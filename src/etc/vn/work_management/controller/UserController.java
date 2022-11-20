package etc.vn.work_management.controller;

import etc.vn.work_management.model.user.Role;
import etc.vn.work_management.model.user.User;
import etc.vn.work_management.service.user.UserServiceImpl;
import etc.vn.work_management.view.adminView.AdminView;
import etc.vn.work_management.view.userView.UserView;

import java.util.List;

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
            new AdminView(userService.findByEmail(email));
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

    public User findUserById(Long id){
        return userService.findById(id);
    }

    public List<User> findAll(){
        return userService.findAll();
    }

}
