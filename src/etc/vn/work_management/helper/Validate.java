package etc.vn.work_management.helper;

import java.util.regex.Pattern;

public class Validate {
    private  static  final Validate INSTANCE=new Validate();

    public static  Validate getInstance(){
        return INSTANCE;
    }

    public boolean isInputString(String string) {
        return Pattern.matches("[a-z0-9_-]{6,}", string);
    }
    public boolean checkInputNumber(String number) {
        return Pattern.matches("[0-9]{1,2}", number);
    }
    public boolean checkInputNumber(int min, int max, String number) {
        String maxNumber = String.valueOf(max);
        String minNumber = String.valueOf(min);
        return Pattern.matches("[" + minNumber + "-" + maxNumber + "]", number);
    }
    public boolean checkPassword(String string) {
        return Pattern.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-_]).{8,}$", string);
    }

    public boolean checkEmail(String string) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", string);
    }
}
