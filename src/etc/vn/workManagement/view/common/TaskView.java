package etc.vn.workManagement.view.common;

import etc.vn.workManagement.WorkManagementApplication;
import etc.vn.workManagement.controller.UserController;
import etc.vn.workManagement.controller.WorkController;
import etc.vn.workManagement.helper.Validate;
import etc.vn.workManagement.model.work.Status;
import etc.vn.workManagement.model.work.Task;

import java.util.Scanner;

public class TaskView {
    UserController userController = UserController.getInstance();
    WorkController workController = WorkController.getInstance();
    CommonView commonView = CommonView.getInstance();
    Validate validate = Validate.getInstance();
    Scanner scanner = new Scanner(System.in);

    public TaskView() {
        commonView.showMessage("============ TASK FORM ============");
        commonView.showMessage(CommonView.MESSAGE_TO_BACK);
        view();
    }

    private void view() {
        commonView.showMessage("1.Tạo task");
        commonView.showMessage("2.Cập nhật task ");
        commonView.showMessage("3.Xóa Task");
        commonView.showMessage("4.Xem Task đã hoàn thành");
        String selection = commonView.inputString("Lựa chọn của bạn : ", scanner);
        while (true) {
            if (!validate.checkInputNumber(0, 4, selection)) {
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
                createTask();
                break;
            case 2:
                updateTask();
                break;
            case 3:
                deleteTask();
                break;
            case 4:
                displayDoneTask();
                break;
            default:
                commonView.showMessage("Chọn lại đi bạn ơi");
                break;
        }
        view();
    }

    private void createTask() {
        commonView.showMessage("============ CREATE TASK FORM ============");
        commonView.showMessage(CommonView.MESSAGE_TO_BACK);
        String title = commonView.inputString("Bước 1 nhập tiêu đề : ", scanner);

        while (true) {
            if (title != null && !title.equals("")) {
                break;
            }
            commonView.showMessage("Tiêu đề cần được đặt ");
            title = commonView.inputString("Xin mời nhập lại tiêu đề : ", scanner);
        }
        String description = commonView.inputString("Bước 2 nhập mô tả : ", scanner);
        Long id = WorkManagementApplication.user.getId();
        Status state = Status.NEW;
        int priority = inputPriority();
        if (priority == -1) {
            return;
        }
        int extimate= inputExtimate();

    }
    private int inputExtimate() {
        commonView.showMessage("Ước tính task 1 trở lên ");
        String priority = commonView.inputString("Bước 4 điểm ước tính của task : ", scanner);
        while (true) {
            if (validate.checkInputNumber(0, 0, priority)) {
                return -1;
            }
            if (validate.checkInputNumber(priority)) {
                break;
            }
            commonView.showMessage("Độ yêu tiên phải là số từ 1 trở lên");
            priority = commonView.inputString("Hãy nhập lại độ ưu tiên của task : ", scanner);
        }
        return Integer.parseInt(priority);
    }

    private int inputPriority() {
        commonView.showMessage("Độ ưu tiên phải bắt đầu từ 1 trở lên ");
        String priority = commonView.inputString("Bước 3 độ ưu tiên của task : ", scanner);
        while (true) {
            if (validate.checkInputNumber(0, 0, priority)) {
                return -1;
            }
            if (validate.checkInputNumber(priority)) {
                break;
            }
            commonView.showMessage("Độ yêu tiên phải là số từ 1 trở lên");
            priority = commonView.inputString("Hãy nhập lại độ ưu tiên của task : ", scanner);
        }
        return Integer.parseInt(priority);
    }



    private void deleteTask() {

    }

    private void updateTask() {

    }

    private void displayDoneTask() {

    }
}
