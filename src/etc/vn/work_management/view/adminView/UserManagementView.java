package etc.vn.work_management.view.adminView;

import etc.vn.work_management.WorkManagementApplication;
import etc.vn.work_management.controller.UserController;
import etc.vn.work_management.controller.WorkController;
import etc.vn.work_management.helper.Validate;
import etc.vn.work_management.view.common.CommonView;

import java.util.Scanner;

public class UserManagementView {
    private UserController userController = UserController.getInstance();
    private WorkController workController = WorkController.getInstance();
    private CommonView commonView = CommonView.getInstance();
    private Validate validate = Validate.getInstance();
    private Scanner scanner = new Scanner(System.in);

    public UserManagementView(){
        view();
    }
    private  void view(){
        commonView.displayObject(WorkManagementApplication.user);
        commonView.showMessage("============ USER MANAGEMENT FORM ============");
        commonView.displayUserList(userController.findAll());
        commonView.showMessage(CommonView.MESSAGE_TO_BACK);
        commonView.showMessage("1.Tạo mới tài khoản");
        commonView.showMessage("2.Đổi mật khẩu");
    }
}
