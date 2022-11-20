package etc.vn.work_management.view.adminView;

import etc.vn.work_management.WorkManagementApplication;
import etc.vn.work_management.controller.UserController;
import etc.vn.work_management.controller.WorkController;
import etc.vn.work_management.helper.Validate;
import etc.vn.work_management.model.user.User;
import etc.vn.work_management.view.common.CommonView;
import etc.vn.work_management.view.common.TaskView;
import etc.vn.work_management.view.userView.InformationUserView;

import java.util.Scanner;

public class AdminView {
    CommonView commonView = CommonView.getInstance();
    Validate validate=Validate.getInstance();
    Scanner scanner =new Scanner(System.in);

   public AdminView(User user) {
       WorkManagementApplication.user = user;
       WorkManagementApplication.isAdmin=true;
       view();
    }
    private void view() {
        commonView.showMessage("Xin chào " + WorkManagementApplication.user.getName());
        commonView.showMessage("============ ADMIN FORM ============");
        commonView.showMessage("1.Quản Lý User ");
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
                new UserManagementView();
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
