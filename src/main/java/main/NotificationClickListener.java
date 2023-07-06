package main;

public interface NotificationClickListener {

    void onNotificationClicked(int identifier);

    void onNotificationActionClicked(int identifier, int index);

}
