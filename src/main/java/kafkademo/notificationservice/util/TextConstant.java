package kafkademo.notificationservice.util;

public class TextConstant {
    public static final String WELCOME_MESSAGE = """
             Welcome! I'm a notification bot from TaskManagerSystems. \
            Enter /link <your email> to associate your \
            email with your TaskManagerSystems account.
            """;
    public static final String INVALID_INPUT_WARNING = """
             Please, enter /link <your email> to associate your \
            telegram with your TaskManagerSystems account.
            """;
    public static final String VERIFICATION_LINK_SENT =
            "Verification link has been sent to your email.";
    public static final String VERIFICATION_LINK =
            "%s/api/auth/confirm?token=%s";
    public static final String EMAIL_TEXT = """
            <h3>Hello! Click on this button, if you want link your Telegram with TMS account:</h3> \
            <a href="%s" style="display: inline-block; padding: 10px 20px;
            font-size: 16px; color: white; background-color: #4CAF50;
            text-align:center; text-decoration: none; border-radius: 5px;"
            >Link Telegram to account</a>""";
    public static final String USER_IS_PRESENT = """
            A link to associate your TMS account with Telegram has been sent to your email. 
            The link is valid for 1 hour.
            """;
    public static final String USER_NOT_PRESENT = """
            No account with email \
            %s was found. Create an account in TMS first.
            """;
}
