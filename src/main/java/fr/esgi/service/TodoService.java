package fr.esgi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.esgi.domain.Todo;

@Service
public interface TodoService {

	List<Todo> getAllTodos();
	
	Todo saveTodo(Todo todo);
	
	Todo update(Todo todo);
	
	Todo findTodo(Long id) throws Exception;
	
	boolean deleteTodo(Long id);
}
