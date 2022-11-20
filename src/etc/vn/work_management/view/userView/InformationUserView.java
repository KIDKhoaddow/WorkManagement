package etc.vn.work_management.view.userView;

import etc.vn.work_management.WorkManagementApplication;
import etc.vn.work_management.controller.UserController;
import etc.vn.work_management.controller.WorkController;
import etc.vn.work_management.helper.Validate;
import etc.vn.work_management.view.common.CommonView;


import java.util.Scanner;

public class InformationUserView {
    private UserController userController = UserController.getInstance();
    private WorkController workController = WorkController.getInstance();
    private CommonView commonView = CommonView.getInstance();
    private Validate validate = Validate.getInstance();
    private Scanner scanner = new Scanner(System.in);

    public InformationUserView() {
        view();
    }

    private void view() {
        commonView.displayObject(WorkManagementApplication.user);
        commonView.showMessage("============ INFORMATION USER FORM ============");
        commonView.showMessage(CommonView.MESSAGE_TO_BACK);
        commonView.showMessage("1.Đổi tên hiện thị ");
        commonView.showMessage("2.Đổi mật khẩu");
        String selection = commonView.inputString("Lựa chọn của bạn : ", scanner);
        while (true) {
            if (!validate.checkInputNumber(0, 3, selection)) {
                commonView.showMessage("Lựa chọn không nằm trong khả năng xử lý !!!");
                selection = commonView.inputString("Mời bạn nhập lại lựa chọn : ", scanner);
            } else {
                break;
            }
        }
        switch (Integer.parseInt(selection)) {
            case 0:
                return;
            case 1:
                changeNameDisplay();
                break;
            case 2:
                changePassword();
                break;
            default:
                commonView.showMessage("Chọn lại đi bạn ơi");
                break;
        }
        view();
    }

    private void changeNameDisplay() {
        commonView.showMessage("============ CHANGE NAME DISPLAY FORM ============");
        commonView.showMessage(CommonView.MESSAGE_TO_BACK);
        commonView.showMessage("Tên hiện tại của bạn là : " + WorkManagementApplication.user.getName());
        String name = commonView.inputString("Tên bạn muốn đổi thành là : ", scanner);
        if (name.equals("0")) {
            return;
        }
        if (name.equals("")) {
            commonView.showMessage("Tên của bạn sẽ giữ nguyên ");
        }
        if (!userController.changeNameDisplay(name)) {
            commonView.showMessage("Đổi tên thất bại");
            changeNameDisplay();
        }
    }

    private void changePassword() {
        commonView.showMessage("============ CHANGE PASSWORD FORM ============");
        commonView.showMessage(CommonView.MESSAGE_TO_BACK);
        String password = checkNewPassword();
        if (password == null) {
            return;
        }
        String passwordConfirm = checkConfirmPassword(password);
        if (passwordConfirm == null) {
            return;
        }
        if (!userController.changePassword(password)) {
            commonView.showMessage("Mật khẩu đã tồn tại ");
        }
    }

    private String checkNewPassword() {
        String password = commonView.inputString("Mật khẩu mới của bạn : ", scanner);
        while (true) {
            if (!validate.checkInputNumber(0, 0, password)) {
                if (!validate.checkPassword(password)) {
                    commonView.showMessage("Sai định dạng rồi bạn ơi ");
                    commonView.showMessage("Một mật khẩu đúng cần có ít nhất một chữ hoa , một chữ thường , một ký tự đặc biệt và có ít nhất là 8 ký tự");
                    commonView.showMessage(CommonView.MESSAGE_TO_EXIT);
                    password = commonView.inputString("Mật khẩu mới của bạn : ", scanner);
                } else {
                    break;
                }
            } else {
                return null;
            }
        }
        return password;
    }


    private String checkConfirmPassword(String password) {
        String confirmPassword = commonView.inputString("Nhập lại mật khẩu mới của bạn : ", scanner);
        while (true) {
            if (!validate.checkInputNumber(0, 0, confirmPassword)) {
                if (!confirmPassword.equals(password)) {
                    commonView.showMessage("Không đúng với mật khẩu mới bạn đã nhập");
                    commonView.showMessage("Bạn hãy nhập lại đúng nhé !");
                    commonView.showMessage(CommonView.MESSAGE_TO_EXIT);
                    confirmPassword = commonView.inputString("Nhập lại mật khẩu mới của bạn : ", scanner);
                } else {
                    break;
                }
            } else {
                return null;
            }
        }
        return confirmPassword;
    }
}
