package traveltrack.helpers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class Utils {
    private final MessageSource messageSource;

    public String getResponseMessage(String errorCode, Map<String, Object> arg, Locale language) {
        String message = messageSource.getMessage(errorCode, null, errorCode, language);
        return Tools.format(message, arg);
    }

}
