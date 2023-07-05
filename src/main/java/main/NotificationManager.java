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

    public native void sayHello();

    public native void helloNotification(String message);

    public void postNotification(String title, String subTitle, NotificationDuration duration) {
        postNotification(title, subTitle, duration.value);
    }

    private native void postNotification(String title, String subTitle, int duration);


    // duration -> done
    // notification with title, sub -> done
    // template -> not working

    // actions, reply1
    // audio
    // expiration
    // image path


    // static
    // temppath
    // native log

    // clear notification
}
