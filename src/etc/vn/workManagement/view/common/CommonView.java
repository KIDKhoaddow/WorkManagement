package etc.vn.workManagement.view.common;

import etc.vn.workManagement.model.user.User;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CommonView {

    public static final String MESSAGE_TO_EXIT = "Nhấn số 0 để có thể thoát chương trình.";
    public static final String MESSAGE_TO_BACK = "0.Quay lại";
    private static final CommonView INSTANCE = new CommonView();

    public static CommonView getInstance() {
        return INSTANCE;
    }

    public int inputInt(String string, Scanner scanner) {
        System.out.print(string);
        return scanner.nextInt();
    }

    public int inputInt(Scanner scanner) {
        return scanner.nextInt();
    }

    public String inputString(String string, Scanner scanner) {
        System.out.print(string);
        return scanner.nextLine();
    }

    public String inputString(Scanner scanner) {
        return scanner.next();
    }

    public double inputDouble(String string, Scanner scanner) {
        System.out.println(string);
        return scanner.nextDouble();
    }

    public void showMessage(String string) {
        System.out.println(string);
    }

    public boolean isInputString(String string) {
        return Pattern.matches("[a-z0-9_-]{6,}", string);
    }


    public void displayObject(Object user) {
        try {
            System.out.println(user);
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void displayUserList(List<User> userList) {
        System.out.format("+---------+-----------------+-----------------+-----------------+---------+-----+--------------+-------------------+---------------------------+%n");
        System.out.format("| User ID |       Name      |    Username     |     Password    |  Role   | Age | Phone Number |      Address      |             Email         |%n");
        System.out.format("+---------+-----------------+-----------------+-----------------+---------+-----+--------------+-------------------+---------------------------+%n");

        for (User element : userList) {
            displayUser(element);
        }
    }

    public void displayUser(User user) {
        String leftAlignFormat = "| %-7s | %-15s | %-15s | %-15s | %-7s | %-3d | %-12s | %-17s | %-25s |%n";
        System.out.format(leftAlignFormat, user.getId(), user.getEmail(), user.getName(),
                user.getPassword());
        System.out.format("+---------+-----------------+-----------------+-----------------+---------+-----+--------------+-------------------+---------------------------+%n");
    }


}
