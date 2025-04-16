package org.example.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IEntityRepository<T> {
    public void save(T entity);
    public void delete(T entity);
    public void update(T entity);
    public Optional<T> findById(int id);
    public List<T> findAll();
}
