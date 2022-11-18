package etc.vn.workManagement.view.adminView;

import etc.vn.workManagement.controller.UserController;
import etc.vn.workManagement.controller.WorkController;
import etc.vn.workManagement.view.common.CommonView;

import java.util.Scanner;

public class AdminView {
    UserController userController = UserController.getInstance();
    WorkController workController = WorkController.getInstance();
    CommonView commonView = CommonView.getInstance();
    Scanner scanner =new Scanner(System.in);
   public AdminView() {
       commonView.showMessage("============ ADMIN FORM ============");
    }
}
