package org.example.repository;

import java.sql.SQLException;
import java.util.List;

public interface IEntityRepository<T> {
    public void save(T entity);
    public void delete(T entity);
    public void update(T entity);
    public T findById(Long id);
    public List<T> findAll();
}
