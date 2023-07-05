package main;

public enum NotificationDuration {
    System(0),
    Short(1),
    Long(2);

    public final int value;

    private NotificationDuration(int value) {
        this.value = value;
    }
}
