package etc.vn.workManagement.service.work;

import etc.vn.workManagement.model.user.Role;
import etc.vn.workManagement.model.user.User;
import etc.vn.workManagement.model.work.Activity;
import etc.vn.workManagement.model.work.Status;
import etc.vn.workManagement.model.work.Task;
import etc.vn.workManagement.service.user.UserServiceImpl;
import etc.vn.workManagement.view.common.CommonView;

import java.util.ArrayList;
import java.util.List;

public class WorkServiceImpl implements IWorkService{
    private static List<Task> taskList = loadTask();
    private static final WorkServiceImpl INSTANCE = new WorkServiceImpl();

    public static WorkServiceImpl getInstance() {
        return INSTANCE;
    }

    private CommonView commonView=CommonView.getInstance();

    private static ArrayList<Task> loadTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Long i = 0L; i <=10L ; i++) {
            tasks.add(new Task(i,"task của khoa"+i,"task của khoa"+i,1L,
                    Status.NEW,2,8,8,8, Activity.TRAINING));
        }
        return tasks;
    }
    @Override
    public void save(Task task) {
        Long id= taskList.get(taskList.size()-1).getId()+1;
        task.setId(id);
        taskList.add(task);
    }

    @Override
    public void edit(int index, Task task) {
        taskList.add(index,task);
    }

    @Override
    public boolean delete(Long id, Task task) {
        return taskList.remove(findById(id));
    }

    @Override
    public List<Task> findAll() {
        return taskList;
    }

    @Override
    public Task findById(Long id) {
        for (Task element: taskList) {
            if(element.getId()==id){
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
    }


}
