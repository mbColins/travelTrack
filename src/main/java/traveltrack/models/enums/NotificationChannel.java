package traveltrack.models.enums;

public enum NotificationChannel {
    SMS("send notification through email"),
    EMAIL("send notifications through email"),
    WHATSAPP("send notifications through whatsapp"),
    BOTH("send notification via both channels");
    private String description;

    NotificationChannel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
