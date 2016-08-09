package com.nisum.training.service;


import com.nisum.training.model.ToDo;
import com.nisum.training.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ToDoService {

    @Autowired
    private TodoRepository todoRepository;

    public ToDo guardarElemento(ToDo myToDo) {
        return todoRepository.save(myToDo);
    }

    public ToDo borrarElemento(ToDo myToDo) {
        if (myToDo == null)
            throw new IllegalArgumentException();

        todoRepository.delete(myToDo.getId());
        return myToDo;
    }

    public ArrayList<ToDo> obtenerTodos() {
        ArrayList<ToDo> resultado = (ArrayList<ToDo>) todoRepository.findAll();
        return resultado;
    }

    public ArrayList<ToDo> obtenerToDoPorCategoria(String categoria) {
        ArrayList<ToDo> resultado = (ArrayList<ToDo>) todoRepository.findByCategory(categoria);
        return resultado;
    }


}
