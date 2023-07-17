package com.notification4J;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class NotificationManager {

    private static String applicationName;
    private static String appUserModelId;
    public static NotificationClickListener listener;
    public static LogHandler logHandler;

    public static void init(String applicationName, String appUserModelId) {
        NotificationManager.applicationName = applicationName;
        NotificationManager.appUserModelId = appUserModelId;
        loadLibrary("/native.dll");
    }

    private static NotificationManager instance;

    private NotificationManager() {
    }

    public static synchronized NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

    private static void loadLibrary(String name) {
        URL url = NotificationManager.class.getResource(name);
        try {
            File nativeLibTmpFile = new File(url.toURI());
            System.load(nativeLibTmpFile.getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static String getApplicationName() {
        return applicationName;
    }

    private static String getAppUserModelId() {
        return appUserModelId;
    }

    /**
     * Posts a notification with the specified title, subtitle, and identifier.
     *
     * @param title      The title of the notification.
     * @param subTitle   The subtitle of the notification.
     * @param identifier The unique identifier for the notification, used for determining notification actions.
     */
    public void postNotification(String title, String subTitle, int identifier) {
        postNotification(title, subTitle, identifier, null, NotificationSound.Default, new String[]{});
    }

    /**
     * Posts a notification with the specified title, subtitle, identifier, and avatar.
     *
     * @param title      The title of the notification.
     * @param subTitle   The subtitle of the notification.
     * @param identifier The unique identifier for the notification, used for determining notification actions.
     * @param avatarPath The file path to the avatar image for the notification.
     */
    public void postNotification(String title, String subTitle, int identifier, String avatarPath) {
        postNotification(title, subTitle, identifier, avatarPath, NotificationSound.Default, new String[]{});
    }

    /**
     * Posts a notification with the specified title, subtitle, identifier, and sound.
     *
     * @param title      The title of the notification.
     * @param subTitle   The subtitle of the notification.
     * @param identifier The unique identifier for the notification, used for determining notification actions.
     * @param sound      The sound to be played for the notification.
     */
    public void postNotification(String title, String subTitle, int identifier, NotificationSound sound) {
        postNotification(title, subTitle, identifier, null, sound, new String[]{});
    }

    /**
     * Posts a notification with the specified title, subtitle, identifier, and actions.
     *
     * @param title      The title of the notification.
     * @param subTitle   The subtitle of the notification.
     * @param identifier The unique identifier for the notification, used for determining notification actions.
     * @param actions    An array of strings representing the actions to be included in the notification.
     */
    public void postNotification(String title, String subTitle, int identifier, String[] actions) {
        postNotification(title, subTitle, identifier, null, NotificationSound.Default, actions);
    }

    /**
     * Posts a notification with the specified title, subtitle, identifier, avatar, and sound.
     *
     * @param title      The title of the notification.
     * @param subTitle   The subtitle of the notification.
     * @param identifier The unique identifier for the notification, used for determining notification actions.
     * @param avatarPath The file path to the avatar image for the notification (optional).
     * @param sound      The sound to be played for the notification.
     */
    public void postNotification(String title, String subTitle, int identifier, String avatarPath, NotificationSound sound) {
        postNotification(title, subTitle, identifier, avatarPath, sound, new String[]{});
    }

    /**
     * Posts a notification with the specified title, subtitle, identifier, avatar, and actions.
     *
     * @param title      The title of the notification.
     * @param subTitle   The subtitle of the notification.
     * @param identifier The unique identifier for the notification, used for determining notification actions.
     * @param avatarPath The file path to the avatar image for the notification (optional).
     * @param actions    An array of strings representing the actions to be included in the notification.
     */
    public void postNotification(String title, String subTitle, int identifier, String avatarPath, String[] actions) {
        postNotification(title, subTitle, identifier, avatarPath, NotificationSound.Default, actions);
    }

    /**
     * Posts a notification with the specified title, subtitle, identifier, sound, and actions.
     *
     * @param title      The title of the notification.
     * @param subTitle   The subtitle of the notification.
     * @param identifier The unique identifier for the notification, used for determining notification actions.
     * @param sound      The sound to be played for the notification.
     * @param actions    An array of strings representing the actions to be included in the notification.
     */
    public void postNotification(String title, String subTitle, int identifier, NotificationSound sound, String[] actions) {
        postNotification(title, subTitle, identifier, null, sound, actions);
    }

    /**
     * Posts a notification with the specified title, subtitle, identifier, avatar, sound, and actions.
     *
     * @param title      The title of the notification.
     * @param subTitle   The subtitle of the notification.
     * @param identifier The unique identifier for the notification, used for determining notification actions.
     * @param avatarPath The file path to the avatar image for the notification (optional).
     * @param sound      The sound to be played for the notification.
     * @param actions    An array of strings representing the actions to be included in the notification.
     */
    public void postNotification(String title, String subTitle, int identifier, String avatarPath, NotificationSound sound, String[] actions) {
        postNotification(title, subTitle, identifier, avatarPath, sound.value, actions);
    }

    private native void postNotification(String title, String subTitle, int identifier, String avatarPath, String sound, String[] actions);

    private static void handleNotificationClick(int identifier) {
        if (listener != null) {
            listener.onNotificationClicked(identifier);
        } else {
            System.out.println("[JNI] Clicked " + identifier);
        }
    }

    private static void handleNotificationActionClick(int identifier, int actionIndex) {
        if (listener != null) {
            listener.onNotificationActionClicked(identifier, actionIndex);
        } else {
            System.out.println("[JNI] Clicked " + identifier + " index " + actionIndex);
        }
    }

    /**
     * Clears all notifications from the notification center.
     * This method removes all active notifications displayed by the library.
     */
    public native void clearNotifications();

    private static void javaLog(String message) {
        if (logHandler != null) {
            logHandler.log(message);
        } else {
            System.out.println("[JNI] " + message);
        }
    }

}
