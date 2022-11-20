package etc.vn.work_management.service.work;

import etc.vn.work_management.model.work.Task;
import etc.vn.work_management.service.IGeneralService;

import java.util.List;

public interface IWorkService extends IGeneralService<Task> {
    List<Task> findTaskByUserId(Long id);

    List<Task> findTaskToDoOrNewByUserId(Long id);

    List<Task> findDoneTask();

}
