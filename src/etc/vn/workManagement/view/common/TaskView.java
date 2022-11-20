package etc.vn.workManagement.view.common;

import etc.vn.workManagement.WorkManagementApplication;
import etc.vn.workManagement.controller.UserController;
import etc.vn.workManagement.controller.WorkController;
import etc.vn.workManagement.helper.Validate;
import etc.vn.workManagement.model.work.Activity;
import etc.vn.workManagement.model.work.Status;
import etc.vn.workManagement.model.work.Task;

import java.util.List;
import java.util.Scanner;

public class TaskView {
    UserController userController = UserController.getInstance();
    WorkController workController = WorkController.getInstance();
    CommonView commonView = CommonView.getInstance();
    Validate validate = Validate.getInstance();
    Scanner scanner = new Scanner(System.in);

    public TaskView() {

        view();
    }

    private void view() {
        commonView.showMessage("============ TASK FORM ============");
        commonView.displayTaskList(workController.findTaskByUserId(WorkManagementApplication.user.getId()));
        commonView.showMessage(CommonView.MESSAGE_TO_BACK);
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
        int priority = inputPoint("Độ ưu tiên");
        if (priority == -1) {
            return;
        }
        int estimate = inputPoint(" Điểm ước tính");
        if (estimate == -1) {
            return;
        }
        int taskPoint = inputPoint("Điểm tính");
        if (taskPoint == -1) {
            return;
        }
        int remainingWork = taskPoint;
        Activity activity = inputActivity();
        Task task = new Task(1L, title, description, WorkManagementApplication.user.getId(),
                state, priority, estimate, remainingWork, taskPoint, activity);
        if (!workController.createTask(task)) {
            commonView.showMessage("Bị lỗi rồi , vui lòng tạo lại thôi");
        }
        commonView.displayObject(task);
    }
    private int inputPoint(String namePoint) {
        commonView.showMessage(namePoint + " task là số từ 1 trở lên ");
        String point = commonView.inputString("Tiêp theo là " + namePoint + " của task : ", scanner);
        while (true) {
            if (validate.checkInputNumber(0, 0, point)) {
                return -1;
            }
            if (validate.checkInputNumber(point)) {
                break;
            }
            commonView.showMessage(namePoint + " task phải là số từ 1 trở lên");
            point = commonView.inputString("Hãy nhập lại " + namePoint + " của task : ", scanner);
        }
        return Integer.parseInt(point);
    }
    private Activity inputActivity() {
        commonView.showMessage(" Cuối cùng hãy chọn trong các mục hành động của task sau đây :");
        for (int i = 1; i <= Activity.values().length; i++) {
            commonView.showMessage(i + "." + Activity.values()[i]);
        }
        String activity = commonView.inputString(CommonView.MESSAGE_TO_CHOOSE, scanner);
        while (true) {
            if (validate.checkInputNumber(0, 0, activity)) {
                return null;
            }
            if (validate.checkInputNumber(activity)) {
                break;
            }
            commonView.showMessage(CommonView.MESSAGE_TO_REJECT_SELECTION);
            activity = commonView.inputString("Hãy nhập lại loại hành động của task : ", scanner);
        }
        switch (Integer.parseInt(activity)) {
            case 1:
                return Activity.DEVELOPMENT;
            case 2:
                return Activity.DEPLOYMENT;
            case 3:
                return Activity.DESIGN;
            case 4:
                return Activity.TRAINING;
            case 5:
                return Activity.TESTING;
            default:
                return null;
        }
    }
    private void deleteTask() {
        List<Task> listTask = workController.findTaskNewOrToDoByUserId(WorkManagementApplication.user.getId());
        commonView.showMessage("============ DELETE TASK FORM ============");
        commonView.displayTaskList(listTask);
        commonView.showMessage(CommonView.MESSAGE_TO_BACK);
        commonView.showMessage("Bạn chỉ có thể xóa task đang làm hoặc mới !!");
        commonView.showMessage("Hãy lựa chọn dựa trên id của task");
        String selection = commonView.inputString(CommonView.MESSAGE_TO_CHOOSE, scanner);
        while (true) {
            if (!validate.checkInputNumber(selection)) {
                commonView.showMessage("Lựa chọn của bạn nằm ngoài khả năng của chương trình mời bạn nhập lại ");
                selection = commonView.inputString(CommonView.MESSAGE_TO_CHOOSE, scanner);
            } else {
                break;
            }
        }
        Long idTask = Long.parseLong(selection);

        if (!idTask.equals(0L)) {
            if (isTaskInList(idTask, listTask)) {
                if (workController.removeTaskById(idTask)) {
                    commonView.showMessage("Xóa Task thành công !!!");
                } else {
                    commonView.showMessage("Xóa không thành công , xin mời bạn thực hiện lại !!");
                }
            } else {
                commonView.showMessage("Id bạn chọn không nằm trong khả năng mà bạn có thể xóa đi ");
            }
        } else {
            return;
        }
        deleteTask();
    }
    boolean isTaskInList(Long id, List<Task> list) {
        for (Task element : list) {
            if (element.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    private void updateTask() {
        commonView.showMessage("============ UPDATE TASK FORM ============");
        commonView.displayTaskList(workController.findTaskByUserId(WorkManagementApplication.user.getId()));
        commonView.showMessage(CommonView.MESSAGE_TO_BACK);
        commonView.showMessage("Bạn chỉ có thể cập nhật  task đang làm hoặc mới !!");
        commonView.showMessage("Step 1 : Hãy lựa chọn dựa trên id của task");
        String selection = commonView.inputString(CommonView.MESSAGE_TO_CHOOSE, scanner);
        while (true) {
            if (!validate.checkInputNumber(selection)) {
                commonView.showMessage(CommonView.MESSAGE_TO_REJECT_SELECTION);
                selection = commonView.inputString(CommonView.MESSAGE_TO_CHOOSE, scanner);
            } else {
                break;
            }
        }
        if (selection.equals("0")) {
            return;
        } else {
            if (updateStepTwo(Long.parseLong(selection))) {
                commonView.showMessage("Cập nhật thành công ");
            } else {
                commonView.showMessage("Cập nhật thất bại rồi , hãy làm lại nhé :)");
            }
        }
    }
    private boolean updateStepTwo(Long id) {
        commonView.displayObject(workController.findById(id));
        commonView.showMessage("Step 2 : Hãy lựa chọn theo tiêu chí");
        commonView.showMessage(CommonView.MESSAGE_TO_BACK);
        commonView.showMessage("1.Cập nhật thông tin (Tiêu đề ,mô tả)");
        commonView.showMessage("2.Cập nhật trạng thái ");
        commonView.showMessage("3.Cập nhật chi tiết (độ quan trọng, ước lượng, còn lại, điểm của task, loại hình task)");
        String selection = commonView.inputString(CommonView.MESSAGE_TO_CHOOSE, scanner);
        while (true) {
            if (!validate.checkInputNumber(0, 3, selection)) {
                commonView.showMessage(CommonView.MESSAGE_TO_REJECT_SELECTION);
                selection = commonView.inputString(CommonView.MESSAGE_TO_CHOOSE, scanner);
            } else {
                if (selection.equals("0")) {
                    return false;
                }
                break;
            }
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                updateInformationTask(id);
                break;
            case 2:
                updateStateTask(id);
                break;
            case 3:
                updateDetailTask(id);
                break;
            default:
                return false;
        }
        return true;
    }
    private void updateInformationTask(Long id) {
        Task task = workController.findById(id);
        displayStepThree(task);
        String title = commonView.inputString("Tiêu đề mới là :", scanner);
        String describe = commonView.inputString("Mô tả mới là :", scanner);
        if (title != null && describe != null) {
            if (!title.equals("")) {
                task.setTitle(title);
            }
            if (!describe.equals("")) {
                task.setDescription(describe);
            }
        }
        commonView.displayObject(workController.updateTask(task));
    }
    private void updateDetailTask(Long id) {
        Task task = workController.findById(id);
        displayStepThree(task);
        int priority = inputPoint("Độ uu tiên mới ");
        if (priority == -1) {
            commonView.showMessage("Cập nhật chi tiết thất bại ");
            return;
        }
        int estimate = inputPoint("Ước tính mới  ");
        if (estimate == -1) {
            commonView.showMessage("Cập nhật chi tiết thất bại ");
            return;
        }
        int remainingWork = inputPoint("Điểm còn lại");
        if (remainingWork == -1) {
            commonView.showMessage("Cập nhật chi tiết thất bại ");
            return;
        }

        int taskPoint = inputPoint("Điểm task");
        if (taskPoint == -1) {
            commonView.showMessage("Cập nhật chi tiết thất bại ");
            return;
        }
        task.setPriority(priority);
        task.setEstimate(estimate);
        task.setRemainingWork(remainingWork);
        task.setTaskPoint(taskPoint);
        commonView.displayObject(workController.updateTask(task));
    }

    private void updateStateTask(Long id) {
        Task task = workController.findById(id);
        displayStepThree(task);
        commonView.showMessage("Chọn một state dưới đây để thay đổi");
        for (int i = 1; i <= Status.values().length; i++) {
            commonView.showMessage(i + ". " + Status.values()[i - 1]);
        }
        String selection = commonView.inputString(CommonView.MESSAGE_TO_CHOOSE, scanner);
        while (true) {
            if (!validate.checkInputNumber(0, Status.values().length, selection)) {
                commonView.showMessage(CommonView.MESSAGE_TO_REJECT_SELECTION);
                selection = commonView.inputString(CommonView.MESSAGE_TO_CHOOSE, scanner);
            } else {
                if (selection.equals("0")) {
                    commonView.showMessage("Cập nhật thất bại trạng thái !!");
                    return;
                }
                break;
            }
        }
        switch (Integer.parseInt(selection)) {
            case 1:
                task.setState(Status.NEW);
                break;
            case 2:
                task.setState(Status.TO_DO);
                break;
            case 3:
                task.setState(Status.IN_PROCESS);
                break;
            case 4:
                task.setState(Status.DONE);
                break;
            default:
                commonView.showMessage("Cập nhật thất bại trạng thái !!");
        }
        commonView.displayObject(workController.updateTask(task));
    }

    private void displayStepThree(Task task) {
        commonView.displayObject(task);
        commonView.showMessage("Step 3 : Hãy điền sự thay đổi theo yêu cầu dưới đây ");
        commonView.showMessage(CommonView.MESSAGE_TO_BACK);
    }

    private void displayDoneTask() {
        commonView.showMessage("============ DONE TASK ============");
        commonView.displayTaskList(workController.displayDoneTask());
    }
}
