package com.nisum.training.service;

import com.nisum.training.model.ToDo;
import com.nisum.training.repository.TodoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ToDoServiceTest {

    // mocks
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private ToDoService toDoService;

    //miembros de clase
    private ToDo myToDo;

    private ToDo toDoFromRepositorio;

    @Before
    public void name() throws Exception {
        //objeto que quiero guardar
        myToDo = new ToDo();
        myToDo.setId(2L);

        //objeto que recibo desde el repo
        toDoFromRepositorio = new ToDo();
        toDoFromRepositorio.setId(1L);
    }

    @Test
    public void probarInstancia() throws Exception {
        ToDoService toDoService = new ToDoService();
        Assert.assertNotNull(toDoService);
    }

    @Test
    public void cuandoGuardoUnToDoDeberiaRetornarElMismoToDo() throws Exception {
        //mock de la clase repositorio (capa inferior)
        when(todoRepository.save(myToDo)).thenReturn(toDoFromRepositorio);

        //llamada real al service (esto es lo que realmente se prueba)
        ToDo resultado =  toDoService.guardarElemento(myToDo);

        //assert
        Assert.assertNotNull(resultado);
        Assert.assertEquals(resultado.getId(),1L);
    }

    @Test
    public void cuandoEliminoUnTodoRetornarElElementoEliminado() throws Exception {
        //arrange

        //act
        // metodo de mockito para hacer mock de funciones void
        doNothing().when(todoRepository).delete(2L);
        ToDo resultado = toDoService.borrarElemento(myToDo);

        //assert
        Assert.assertNotNull(resultado);
        Assert.assertEquals(resultado.getId(),2L);
        verify(todoRepository).delete(2L);
    }

    @Test (expected = IllegalArgumentException.class)
    public void cuandoEliminoUnToDoNullLanzaException() throws Exception {
        ToDo objetoAEliminar = null;

        //Mockito.doThrow(new Exception()).when(instance).methodName();
        doThrow(new IllegalArgumentException()).when(todoRepository).delete(objetoAEliminar);
        toDoService.borrarElemento(objetoAEliminar);
    }

    @Test
    public void obtenerTodosLosToDo() throws Exception {
        //arrange
        List<ToDo> misToDos = new ArrayList<ToDo>();

        //act
        when(todoRepository.findAll()).thenReturn(misToDos);
        ArrayList<ToDo> resultado = toDoService.obtenerTodos();

        //assert
        Assert.assertNotNull(resultado);
        Assert.assertEquals(resultado,misToDos);
        Assert.assertEquals(resultado.size(),0);
    }

    @Test
    public void obtenerToDoPorCategoria() throws Exception {
        //arrange
        ArrayList<ToDo> misToDos = new ArrayList<ToDo>();


        //act
        when(todoRepository.findByCategory(anyString())).thenReturn(misToDos);
        ArrayList<ToDo> resultado = toDoService.obtenerToDoPorCategoria("trabajo");

        //assert
        Assert.assertNotNull(resultado);
        Assert.assertEquals(resultado,misToDos);
        Assert.assertEquals(resultado.size(),0);
    }

    @Test
    public void cuandoActualizoUnToDoRetornaElToDoActualizado() throws Exception {
        myToDo.setCategoria("hogar");
        myToDo.setDescripcion("comprar en el supermercado");
        myToDo.setEstado(true);

        ToDo objetoActualizado = myToDo;
        objetoActualizado.setEstado(false);

        when(todoRepository.save(myToDo)).thenReturn(objetoActualizado);
        ToDo resultado = toDoService.actualizarToDo(myToDo);

        Assert.assertNotNull(resultado);
        Assert.assertEquals(resultado.isEstado(), false);
    }
}
