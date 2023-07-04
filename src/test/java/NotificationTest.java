import main.NotificationManager;
import org.junit.Before;
import org.junit.Test;

public class NotificationTest {

    @Before
    public void prewarm() {
        NotificationManager.init();
    }

    @Test
    public void test() {
        new NotificationManager().sayHello();
    }

    @Test
    public void test2() {
        new NotificationManager().helloNotification("First Notification");
    }

}
