import com.notification4J.NotificationClickListener;
import com.notification4J.NotificationManager;
import com.notification4J.NotificationSound;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Semaphore;

public class NotificationTest {

    @Before
    public void prewarm() {
        NotificationManager.init("Notification4J", "Notification4J.ID");
        NotificationManager.listener = new NotificationClickListener() {
            @Override
            public void onNotificationClicked(int identifier) {
                System.out.println("Clicked " + identifier);
            }

            @Override
            public void onNotificationActionClicked(int identifier, int index) {
                System.out.println("Clicked " + identifier + " index " + index);
            }
        };

        NotificationManager.logHandler = message -> {
            System.out.println(message);
        };
    }

    @Test
    public void test1() throws InterruptedException {
        Semaphore s = new Semaphore(0);
        NotificationManager.getInstance().postNotification("First Notification",
                "Hello, World!", 1, "C:\\Users\\preet\\Downloads\\381C6E81-9F96-4620-A55B-FF91D5F082BB.png",
                NotificationSound.Mail, new String[]{"Get Started"});

//        new NotificationManager().postNotification("Title", "Subtitle 2", NotificationDuration.Short);
//        new NotificationManager().postNotification("Title", "Subtitle 3", NotificationDuration.Long);
        s.acquire();
    }

}
