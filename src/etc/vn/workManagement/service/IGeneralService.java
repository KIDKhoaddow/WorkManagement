package etc.vn.workManagement.service;

import java.util.List;

public interface IGeneralService <T> {

    void save(T t);

    void edit(int index, T t);

    boolean delete(Long id, T t);

    List<T> findAll();

    T findById(Long id);

    void displayById(Long id);

    void displayALl();

}
