package View.Printer;

public class RegisterPrinter extends Printer {

    public static void printFormatError(String string) {
        print(string + " format is not valid");
    }

    public static void printPasswordSafetyError() {
        print("password is weak");
    }

    public static void printRepetitiousUsername(String username) {
        print("user with username " + username + " already exists");
    }

    public static void printRepetitousNickName(String nickname) {
        print("user with nickname " + nickname + " already exists");
    }

    public static void printSuccessfulRegister() {
        print("user created successfully!");
    }

    public static void printInvalidLogin() {
        print("Username and password didn't match!");
    }

    public static void printLoginSuccessful() {
        print("user logged in successfully!");
    }

    public static void printCurrentMenu() {
        print("Login Menu");
    }

    public static void printCanNotNavigate() {
        print("menu navigation is not possible");
    }
}