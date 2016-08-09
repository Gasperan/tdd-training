package com.nisum.training.repository;

import com.nisum.training.model.ToDo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<ToDo,Long> {
    @Query("select t from ToDo t where id=?1")
    ToDo findToDoById(Long id);

    @Query("select t from ToDo t where categoria=?1")
    List<ToDo> findByCategory(String category);
}
