import main.NotificationDuration;
import main.NotificationManager;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Semaphore;

public class NotificationTest {

    @Before
    public void prewarm() {
        NotificationManager.init("Notification", "Notification.ID");
    }

    @Test
    public void test() {
        new NotificationManager().sayHello();
    }

    @Test
    public void test2() {
        new NotificationManager().helloNotification("First Notification");
    }

    @Test
    public void test3() throws InterruptedException {
//        Semaphore s = new Semaphore(0);
        new NotificationManager().postNotification("Title", "Subtitle 1", NotificationDuration.System);
//        new NotificationManager().postNotification("Title", "Subtitle 2", NotificationDuration.Short);
//        new NotificationManager().postNotification("Title", "Subtitle 3", NotificationDuration.Long);
//        s.acquire();
    }

}
