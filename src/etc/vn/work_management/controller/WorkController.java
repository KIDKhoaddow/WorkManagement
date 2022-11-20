package etc.vn.work_management.controller;

import etc.vn.work_management.model.work.Task;
import etc.vn.work_management.service.work.WorkServiceImpl;
import etc.vn.work_management.view.common.CommonView;

import java.util.List;

public class WorkController {
    private static final WorkController INSTANCE = new WorkController();

    public static WorkController getInstance() {
        return INSTANCE;
    }

    WorkServiceImpl workService = WorkServiceImpl.getInstance();
    CommonView commonView = CommonView.getInstance();

    public boolean createTask(Task task) {
        try {
            workService.save(task);
        } catch (Exception e) {
            commonView.displayObject(e);
            return false;
        }
        return true;
    }

    public List<Task> findAll() {
        return workService.findAll();
    }

    public List<Task> findTaskByUserId(Long id){
        return workService.findTaskByUserId(id);
    }

    public  List<Task> findTaskNewOrToDoByUserId(Long id){
        return workService.findTaskToDoOrNewByUserId(id);
    }
    public  List<Task> displayDoneTask(){
        return workService.findDoneTask();
    }

    public  boolean removeTaskById(Long id){
        return workService.delete(id);
    }

    public  Task findById(Long id){
        return workService.findById(id);
    }

    public  Task updateTask(Task task){
        return workService.edit(task);
    }
}
