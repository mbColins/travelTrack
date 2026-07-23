package traveltrack.models.enums;

public enum Status {
    BLOCKED("user blocked");
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    Status(String description) {
        this.description = description;
    }
}
