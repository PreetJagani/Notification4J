package com.notification4J;

public enum NotificationSound {

    Default("Default"),
    IM("IM"),
    Mail("Mail"),
    Reminder("Reminder"),
    SMS("SMS"),
    Alarm("Alarm"),
    Alarm2("Alarm2"),
    Alarm3("Alarm3"),
    Alarm4("Alarm4"),
    Alarm5("Alarm5"),
    Alarm6("Alarm6"),
    Alarm7("Alarm7"),
    Alarm8("Alarm8"),
    Alarm9("Alarm9"),
    Alarm10("Alarm10"),
    Call("Call"),
    Call1("Call1"),
    Call2("Call2"),
    Call3("Call3"),
    Call4("Call4"),
    Call5("Call5"),
    Call6("Call6"),
    Call7("Call7"),
    Call8("Call8"),
    Call9("Call9"),
    Call10("Call10");

    public final String value;

    NotificationSound(String sound) {
        this.value = sound;
    }
}