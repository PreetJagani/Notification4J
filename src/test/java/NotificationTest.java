import main.NotificationDuration;
import main.NotificationManager;
import main.NotificationSound;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Semaphore;

public class NotificationTest {

    @Before
    public void prewarm() {
        NotificationManager.init("Notification", "Notification.ID");
    }

    @Test
    public void test1() throws InterruptedException {
//        Semaphore s = new Semaphore(0);
        new NotificationManager().postNotification("Title", "Subtitle 1", NotificationDuration.System, NotificationSound.Mail);
//        new NotificationManager().postNotification("Title", "Subtitle 2", NotificationDuration.Short);
//        new NotificationManager().postNotification("Title", "Subtitle 3", NotificationDuration.Long);
//        s.acquire();
    }

}
