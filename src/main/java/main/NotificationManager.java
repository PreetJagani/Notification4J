package main;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

public class NotificationManager {

    private static String applicationName;
    private static String appUserModelId;


    public static void init(String applicationName, String appUserModelId) {
        NotificationManager.applicationName = applicationName;
        NotificationManager.appUserModelId = appUserModelId;
        loadLibrary("/native.dll");
    }

    //TODO: Make only one temp and load it from there
    private static void loadLibrary(String name) {
        URL url = NotificationManager.class.getResource(name);
        if (url != null) {
            try {
                File tmpDir = Files.createTempDirectory("Notification.native").toFile();
                tmpDir.deleteOnExit();
                File nativeLibTmpFile = new File(tmpDir, name);
                nativeLibTmpFile.deleteOnExit();
                try (InputStream in = url.openStream()) {
                    Files.copy(in, nativeLibTmpFile.toPath());
                }
                System.load(nativeLibTmpFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getApplicationName() {
        return applicationName;
    }

    public static String getAppUserModelId() {
        return appUserModelId;
    }

    public void postNotification(String title, String subTitle, int identifier) {
        postNotification(title, subTitle, identifier, null, NotificationSound.Default, new String[]{});
    }

    public void postNotification(String title, String subTitle, int identifier, String avatarPath) {
        postNotification(title, subTitle, identifier, avatarPath, NotificationSound.Default, new String[]{});
    }

    public void postNotification(String title, String subTitle, int identifier, NotificationSound sound) {
        postNotification(title, subTitle, identifier, null, sound, new String[]{});
    }

    public void postNotification(String title, String subTitle, int identifier, String[] actions) {
        postNotification(title, subTitle, identifier, null, NotificationSound.Default, actions);
    }

    public void postNotification(String title, String subTitle, int identifier, String avatarPath, NotificationSound sound) {
        postNotification(title, subTitle, identifier, avatarPath, sound, new String[]{});
    }

    public void postNotification(String title, String subTitle, int identifier, String avatarPath, String[] actions) {
        postNotification(title, subTitle, identifier, avatarPath, NotificationSound.Default, actions);
    }

    public void postNotification(String title, String subTitle, int identifier, NotificationSound sound, String[] actions) {
        postNotification(title, subTitle, identifier, null, sound, actions);
    }

    public void postNotification(String title, String subTitle, int identifier, String avatarPath, NotificationSound sound, String[] actions) {
        postNotification(title, subTitle, identifier, avatarPath, sound.value, actions);
    }

    private native void postNotification(String title, String subTitle, int identifier, String avatarPath, String sound, String[] actions);

    public static void handleNotificationClick(int identifier) {
        System.out.println("[JNI] Clicked " + identifier);
    }

    public static void handleNotificationActionClick(int identifier, int actionIndex) {
        System.out.println("[JNI] Clicked " + identifier + " index " + actionIndex);
    }

    public native void clearNotifications();

    public static void javaLog(String message) {
        System.out.println("[JNI] " + message);
    }

}
