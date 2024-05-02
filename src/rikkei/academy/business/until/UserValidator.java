package rikkei.academy.business.until;

import rikkei.academy.business.model.User;

import java.util.List;
import java.util.regex.Pattern;

public class UserValidator {

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^(?=.*[A-Z]).{4,}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^.{4,}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$");

    public static boolean validateUsername(String username) {
        return USERNAME_PATTERN.matcher(username).matches();
    }

    public static boolean validatePassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
    public static boolean isEmailUnique(String email) {
        List<User> emailList=IOFile.readFromFile(IOFile.USERS_PATH);
        for (User gmail : emailList) {
            if (gmail.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }

}
