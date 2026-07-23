package traveltrack.helpers;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

    private static final Pattern NAMED_PLACEHOLDER_PATTERN = Pattern.compile("\\{([a-zA-Z0-9_]+)\\}");
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final int OTP_LENGTH = 6;
    private static final int BOUND = 1_000_000;

    @Value("${app.admin.ecpKys:admin@traveltrack.local}")
    private static SecretKey key;

    public static String format(String message, Map<String, Object> args) {
        if (message == null || args == null || args.isEmpty()) {
            return message;
        }

        Matcher matcher = NAMED_PLACEHOLDER_PATTERN.matcher(message);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String placeholder = matcher.group(1);
            Object replacement = args.get(placeholder);
            matcher.appendReplacement(sb, replacement != null ? Matcher.quoteReplacement(replacement.toString()) : "{" + placeholder + "}");
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    public String generateOtp() {
        int number = secureRandom.nextInt(BOUND);
        return String.format("%06d", number);
    }
    public String generateAlphanumericOtp() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            sb.append(chars.charAt(secureRandom.nextInt(chars.length())));
        }
        return sb.toString();
    }




    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    public static String encrypt(String input) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        byte[] iv = new byte[12];
        new SecureRandom().nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);

        byte[] cipherText = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));

        // Prepend IV so decrypt() can extract it later — IV isn't secret,
        // just needs to be unique per encryption and available for decryption.
        ByteBuffer buffer = ByteBuffer.allocate(iv.length + cipherText.length);
        buffer.put(iv);
        buffer.put(cipherText);

        return Base64.getEncoder().encodeToString(buffer.array());
    }
    public static String decrypt(String encoded) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(encoded);

        ByteBuffer buffer = ByteBuffer.wrap(decoded);
        byte[] iv = new byte[12];
        buffer.get(iv);
        byte[] cipherText = new byte[buffer.remaining()];
        buffer.get(cipherText);

        GCMParameterSpec spec = new GCMParameterSpec(128, iv);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key, spec);

        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText, StandardCharsets.UTF_8);
    }
}
