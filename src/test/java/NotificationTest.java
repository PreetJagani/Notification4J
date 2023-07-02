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

}
