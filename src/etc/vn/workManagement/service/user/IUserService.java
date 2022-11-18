package etc.vn.workManagement.service.user;

import etc.vn.workManagement.model.user.Role;
import etc.vn.workManagement.model.user.User;
import etc.vn.workManagement.model.work.Task;
import etc.vn.workManagement.service.IGeneralService;

public interface IUserService extends IGeneralService<User> {
    boolean checkAccount(String email , String password);
    Role checkRole(String email);

    User findByEmail(String email);

    boolean isExitedEmail(String email);

    boolean changeNameDisplay(String nameDisplay);
    boolean changePassword(String newPassword);
}
