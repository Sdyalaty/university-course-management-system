import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Notification> channels = new ArrayList<>();

        channels.add(new Notification.EmailNotification());
        channels.add(new Notification.SMSNotification());
        channels.add(new Notification.InAppNotification());

        notifyAll(channels, "student01", "You have successfully enrolled in CS101.");
        notifyAll(channels, "student01", "Your grade for CS101 has been posted.");
        notifyAll(channels, "student01", "You have dropped CS101.");
    }

    public static void notifyAll(List<Notification> channels, String recipient, String message) {
        for (Notification channel : channels) {
            channel.send(recipient, message);
        }
    }
}
