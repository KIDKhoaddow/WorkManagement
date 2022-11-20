package etc.vn.workManagement.service.work;

import etc.vn.workManagement.model.work.Task;
import etc.vn.workManagement.service.IGeneralService;

import java.util.List;

public interface IWorkService extends IGeneralService<Task> {
    List<Task> findTaskByUserId(Long id);

    List<Task> findTaskToDoOrNewByUserId(Long id);

    List<Task> findDoneTask();

}
