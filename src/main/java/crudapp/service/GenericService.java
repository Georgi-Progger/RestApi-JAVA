package crudapp.service;

import crudapp.repository.GenericRepository;

import java.util.List;

public class GenericService<T,ID> {
    GenericRepository<T, ID> repository;

    public GenericService(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }

    public List<T> getAll() {
        return repository.getAll();
    }

    public T getById(ID id) {
        return repository.getById(id);
    }

    public T save(T t) {
        return repository.create(t);
    }

    public T update(T t) {
        return repository.update(t);
    }

    public void delete(ID id) {
        repository.delete(id);
    }
}