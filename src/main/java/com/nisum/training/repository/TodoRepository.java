package com.nisum.training.repository;

import com.nisum.training.model.ToDo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<ToDo,Long> {
    @Query("select t from ToDo t where id=?1")
    ToDo findToDoById(Long id);
}
