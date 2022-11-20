package etc.vn.work_management.service.user;

import etc.vn.work_management.model.user.Role;
import etc.vn.work_management.model.user.User;
import etc.vn.work_management.service.IGeneralService;

public interface IUserService extends IGeneralService<User> {
    boolean checkAccount(String email , String password);
    Role checkRole(String email);

    User findByEmail(String email);

    boolean isExitedEmail(String email);

    boolean changeNameDisplay(String nameDisplay);
    boolean changePassword(String newPassword);
}
