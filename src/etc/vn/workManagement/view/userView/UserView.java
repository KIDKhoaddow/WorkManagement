package etc.vn.workManagement.view.userView;

import etc.vn.workManagement.WorkManagementApplication;
import etc.vn.workManagement.controller.UserController;
import etc.vn.workManagement.controller.WorkController;
import etc.vn.workManagement.helper.Validate;
import etc.vn.workManagement.model.user.User;
import etc.vn.workManagement.view.common.CommonView;
import etc.vn.workManagement.view.common.TaskView;

import java.util.Scanner;

public class UserView {
    UserController userController = UserController.getInstance();
    WorkController workController = WorkController.getInstance();
    CommonView commonView = CommonView.getInstance();
    Validate validate = Validate.getInstance();
    Scanner scanner = new Scanner(System.in);

    public UserView(User user) {
        WorkManagementApplication.user = user;
        view();
    }

    private void view() {
        commonView.showMessage("Xin chào " + WorkManagementApplication.user.getName());
        commonView.showMessage("============ USER FORM ============");
        commonView.showMessage("1.Thông tin cá nhân ");
        commonView.showMessage("2.Task");
        commonView.showMessage("0.Thoát chương trình");
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
                new InformationUserView();
                break;
            case 2:
                new TaskView();
                break;
            default:
                commonView.showMessage("Chọn lại đi bạn ơi");
                break;
        }
        view();
    }
}
