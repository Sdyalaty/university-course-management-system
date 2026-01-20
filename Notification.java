public interface Notification {
    void send(String recipient, String message);

    static class EmailNotification implements Notification {
        @Override
        public void send(String recipient, String message) {
            System.out.println("Email to " + recipient + ": " + message);
        }
    }

    static class SMSNotification implements Notification {
        @Override
        public void send(String recipient, String message) {
            System.out.println("SMS to " + recipient + ": " + message);
        }
    }

    static class InAppNotification implements Notification {
        @Override
        public void send(String recipient, String message) {
            System.out.println("In-App to " + recipient + ": " + message);
        }
    }
}
