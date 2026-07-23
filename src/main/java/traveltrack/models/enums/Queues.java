package traveltrack.models.enums;

public enum Queues {
    EMAIL_QUEUE("travel_track_email_queue"),
    SMS_QUEUE("travel_track_sms_queue"),
    WHATS_APP_QUEUE("travel_track_whatsapp_queue");
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    Queues(String description) {
        this.description = description;
    }
}
