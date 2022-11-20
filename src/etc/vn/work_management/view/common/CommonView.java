package etc.vn.work_management.view.common;

import etc.vn.work_management.controller.UserController;
import etc.vn.work_management.model.user.User;
import etc.vn.work_management.model.work.Task;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CommonView {

    public static final String MESSAGE_TO_EXIT = "Nhấn số 0 để có thể thoát chương trình.";
    public static final String MESSAGE_TO_BACK = "0.Quay lại";
    public static final  String MESSAGE_TO_CHOOSE="Lựa chọn của bạn là : ";
    public static final String MESSAGE_TO_REJECT_SELECTION=" Sự lựa chọn của bạn nằm ngoài khả năng ứng dụng !!";
    public static  final String MESSAGE_TO_CHOOSE_ID="Hãy chọn theo id của người dùng để chọn người làm task";
    private static final CommonView INSTANCE = new CommonView();

    public static CommonView getInstance() {
        return INSTANCE;
    }

      UserController userController=UserController.getInstance();

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
        System.out.format("+---------+-------------------------+-----------------++%n");
        System.out.format("| User ID |           Email         |    Username     | %n");
        System.out.format("+---------+-------------------------+-----------------++%n");

        for (User element : userList) {
            displayUser(element);
        }
    }

    public void displayUser(User user) {
        String leftAlignFormat = "| %-7s | %-23s | %-15s |%n";
        System.out.format(leftAlignFormat, user.getId(), user.getEmail(), user.getName());
        System.out.format("+---------+-------------------------+-----------------++%n");
    }

    public void displayTaskList(List<Task> tasks) {
        System.out.format("+---------+-----------------+-----------------+-----------------+---------+----------+--------------+-------------------+---------------+-----------+%n");
        System.out.format("| Task ID |       Title     |    Describe     |     userName    |  State  | Priority |   Estimate   |  Remaining Work   |   Task Point  |  Activity |%n");
        System.out.format("+---------+-----------------+-----------------+-----------------+---------+----------+--------------+-------------------+---------------+-----------+%n");

        for (Task element : tasks) {
            displayTask(element);
        }
    }

    public void displayTask(Task task) {

        String leftAlignFormat = "| %-7s | %-15s | %-15s | %-15s | %-7s | %-8d | %-12d | %-17d | %-13d | %-11s |%n";

        String userName=UserController.getInstance().findUserById(task.getIdUser()).getName();


        System.out.format(leftAlignFormat,task.getId(),task.getTitle(),task.getDescription(),
                userName,task.getState(),task.getPriority(),
                task.getEstimate(),task.getRemainingWork(),task.getTaskPoint(),task.getActivity());
        System.out.format("+---------+-----------------+-----------------+-----------------+---------+----------+--------------+-------------------+---------------+-----------+%n");
    }
}
