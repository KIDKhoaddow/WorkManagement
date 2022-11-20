package etc.vn.work_management;

import etc.vn.work_management.controller.UserController;
import etc.vn.work_management.helper.Validate;
import etc.vn.work_management.model.user.User;
import etc.vn.work_management.view.common.CommonView;

import java.util.Scanner;

public class WorkManagementApplication {
    static final Validate validate = Validate.getInstance();
    public static User user;
    public  static boolean isAdmin;
    UserController userController = UserController.getInstance();
    Scanner scanner = new Scanner(System.in);
    CommonView commonView = CommonView.getInstance();
    public WorkManagementApplication() {

        commonView.showMessage("Chào mừng đến phần mềm quản lý công việc");
        commonView.showMessage("Vui lòng hãy nhập email và mật khẩu của bạn để có thể đăng nhập !");
        commonView.showMessage("============ LOGIN FORM ============");
        String email = checkEmail();
        String password = checkPassword();
        if (email != null && password != null) {
            if (userController.checkAccount(email, password)) {
                commonView.showMessage("Đăng nhập thành công");
                userController.login(email);
            }else {
                commonView.showMessage("Sai mật khẩu rồi bạn ơi ");
            }
        }
        new WorkManagementApplication();
    }
    private String checkEmail() {
        commonView.showMessage(CommonView.MESSAGE_TO_EXIT);
        String email = commonView.inputString("Email của bạn : ", scanner);
        while (true) {
            if (!validate.checkInputNumber(0, 0, email)) {
                if (!validate.checkEmail(email)) {
                    commonView.showMessage("Sai định dạng rồi bạn ơi ");
                    commonView.showMessage("Một email đúng cần có  @ và .");
                    commonView.showMessage(CommonView.MESSAGE_TO_EXIT);
                    email = commonView.inputString("Email của bạn : ", scanner);
                } else {
                    break;
                }
            } else {
                return null;
            }
        }
        return email;
    }

    private String checkPassword() {
        commonView.showMessage(CommonView.MESSAGE_TO_EXIT);
        String password = commonView.inputString("Password của bạn : ", scanner);
        while (true) {
            if (!validate.checkInputNumber(0, 0, password)) {
                if (!validate.checkPassword(password)) {
                    commonView.showMessage("Sai định dạng rồi bạn ơi ");
                    commonView.showMessage("Một mật khẩu đúng cần có ít nhất một chữ hoa , một chữ thường , một ký tự đặc biệt và có ít nhất là 8 ký tự");
                    commonView.showMessage(CommonView.MESSAGE_TO_EXIT);
                    password = commonView.inputString("Password của bạn : ", scanner);
                } else {
                    break;
                }

            } else {
                return null;
            }
        }
        return password;
    }

    public static void main(String[] args) {
        new WorkManagementApplication();
    }
}
