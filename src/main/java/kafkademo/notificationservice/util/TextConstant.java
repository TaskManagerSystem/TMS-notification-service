package kafkademo.notificationservice.util;

public class TextConstant {
    public static final String WELCOME_MESSAGE = """
             Welcome! I'm a notification bot from TaskManagerSystems. \
            Enter /link <your email> to associate your \
            email with your TaskManagerSystems account.
            """;
    public static final String INVALID_EMAIL_WARNING = """
             Please, enter /link <your email> to associate your \
            email with your TaskManagerSystems account.
            """;
    public static final String VERIFICATION_LINK_SENT =
            "Verification link has been sent to your email.";
    public static final String VERIFICATION_LINK =
            "%s/api/auth/confirm?token=%s";
}
