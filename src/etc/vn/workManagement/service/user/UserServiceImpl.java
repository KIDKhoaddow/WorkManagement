
package etc.vn.workManagement.service.user;


import etc.vn.workManagement.WorkManagementApplication;
import etc.vn.workManagement.model.user.Role;
import etc.vn.workManagement.model.user.User;
import etc.vn.workManagement.view.common.CommonView;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements IUserService {
    CommonView commonView = CommonView.getInstance();
    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    private static final List<User> userList = loadUser();

    private static ArrayList<User> loadUser() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1L, "khoa1@gmail.com", "Phạm Đăng Khoa", "Darkhunter_97", Role.ADMIN));
        for (Long i = 2l; i <= 10l; i++) {
            users.add(new User(i, "khoa" + i + "@gmail.com", "Phạm Đăng Khoa", "Darkhunter_97"));
        }
        return users;
    }


    @Override
    public void save(User user) {
        userList.add(user);
    }

    @Override
    public void edit(int index, User user) {
        userList.add(index, user);
    }

    @Override
    public boolean delete(Long id, User user) {
        return delete(id, user);
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public User findById(Long id) {
        for (User element : userList) {
            if (element.getId().equals(id)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public void displayById(Long id) {
        commonView.displayObject(findById(id));
    }

    @Override
    public void displayALl() {
        for (User element : userList) {
            commonView.displayObject(element);
        }
    }

    @Override
    public boolean checkAccount(String email, String password) {
        for (User element : userList) {
            if (element.getEmail().equals(email) && element.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User findByEmail(String email) {
        for (User element : userList) {
            if (element.getEmail().equals(email)) {
                return element;
            }
        }

        return null;
    }


    @Override
    public boolean isExitedEmail(String email) {
        for (User element : userList) {
            if (element.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Role checkRole(String email) {
        return findByEmail(email).getRole();
    }


    @Override
    public boolean changeNameDisplay(String nameDisplay) {
        int index;
        try {
            index = userList.indexOf(WorkManagementApplication.user);
            WorkManagementApplication.user.setName(nameDisplay);
            userList.add(index, WorkManagementApplication.user);

        } catch (Exception e) {
            commonView.displayObject(e);
        }
        return true;
    }


    @Override
    public boolean changePassword(String newPassword) {
        int index;
        try{
            index = userList.indexOf(WorkManagementApplication.user);
            if(WorkManagementApplication.user.getPassword().equals(newPassword)){
             return false;
            }
            WorkManagementApplication.user.setPassword(newPassword);
            userList.add(index, WorkManagementApplication.user);
        }catch (Exception e ){
            commonView.displayObject(e);
            return false;
        }
        return true;
    }
}
