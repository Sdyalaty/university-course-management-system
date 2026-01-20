public interface Notification {
    void send(String recipient, String message);

    class EmailNotification implements Notification {
        @Override
        public void send(String recipient, String message) {
            System.out.println("Email to " + recipient + ": " + message);
        }
    }

    class SMSNotification implements Notification {
        @Override
        public void send(String recipient, String message) {
            System.out.println("SMS to " + recipient + ": " + message);
        }
    }

    class InAppNotification implements Notification {
        @Override
        public void send(String recipient, String message) {
            System.out.println("In-App to " + recipient + ": " + message);
        }
    }
}
