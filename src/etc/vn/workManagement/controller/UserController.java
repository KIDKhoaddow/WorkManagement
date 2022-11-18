package etc.vn.workManagement.controller;

import etc.vn.workManagement.helper.Validate;
import etc.vn.workManagement.model.user.Role;
import etc.vn.workManagement.service.user.UserServiceImpl;
import etc.vn.workManagement.view.adminView.AdminView;
import etc.vn.workManagement.view.userView.UserView;

public class UserController {

    private static final UserController INSTANCE = new UserController();

    public static UserController getInstance() {
        return INSTANCE;
    }

    UserServiceImpl userService = UserServiceImpl.getInstance();

    public boolean checkAccount(String email, String password) {
        return userService.checkAccount(email, password);
    }

    public void login(String email) {
        if (userService.checkRole(email).equals(Role.ADMIN)) {
            new AdminView();
        } else {
            new UserView(userService.findByEmail(email));
        }
    }

    public boolean changeNameDisplay(String nameDisplay) {
        try {
            userService.changeNameDisplay(nameDisplay);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean changePassword(String newPassword) {
        return userService.changePassword(newPassword);
    }


}
