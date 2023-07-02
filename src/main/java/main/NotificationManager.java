package main;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

public class NotificationManager {

    private String applicationName;

    public static void init() {
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

    public native void sayHello();
}
