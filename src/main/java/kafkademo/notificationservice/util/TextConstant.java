package kafkademo.notificationservice.util;

public class TextConstant {
    public static final String WELCOME_MESSAGE = """
             Welcome! I'm a notification bot from TaskManagerSystems. \
            Enter /link <your email> to associate your \
            email with your TaskManagerSystems account.
            """;
    public static final String EMPTY_EMAIL_WARNING = """
             Please, enter /link <your email> to associate your \
            email with your TaskManagerSystems account.
            """;
    public static final String EMAIL_RESPONSE = "Your email: %s";
}
