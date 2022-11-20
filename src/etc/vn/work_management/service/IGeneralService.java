package etc.vn.work_management.service;

import java.util.List;

public interface IGeneralService <T> {

    void save(T t);

    void edit(int index, T t);

    boolean delete(Long idTask);

    List<T> findAll();

    T findById(Long id);

}
