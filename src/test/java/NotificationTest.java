import main.NotificationManager;
import main.NotificationSound;
import org.junit.Before;
import org.junit.Test;

public class NotificationTest {

    @Before
    public void prewarm() {
        NotificationManager.init("Notification", "Notification.ID");
    }

    @Test
    public void test1() throws InterruptedException {
//        Semaphore s = new Semaphore(0);
        new NotificationManager().postNotification("Title", "Subtitle 1", "C:\\Users\\preet\\Downloads\\381C6E81-9F96-4620-A55B-FF91D5F082BB.png", NotificationSound.Mail);
//        new NotificationManager().postNotification("Title", "Subtitle 2", NotificationDuration.Short);
//        new NotificationManager().postNotification("Title", "Subtitle 3", NotificationDuration.Long);
//        s.acquire();
    }

}
