package fr.esgi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.esgi.dao.TodoRepository;
import fr.esgi.domain.Todo;
import fr.esgi.service.TodoService;
@Service("TodoService")
public class TodoServiceImpl implements TodoService {
	@Autowired
	private TodoRepository todoRepository;

	@Override
	public List<Todo> getAllTodos() {
		return todoRepository.findAll();
	}

	@Override
	public Todo saveTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	@Override
	public Todo update(Todo todo) {
		return todoRepository.saveAndFlush(todo);
	}

	@Override
	public Todo findTodo(Long id) throws Exception {
		Optional<Todo> todo = todoRepository.findById(id);
		
		if (todo.isPresent()) {
			return todo.get();
		} else {
			throw new Exception("Erreur");
		}
	}

	@Override
	public boolean deleteTodo(Long id) {
		todoRepository.deleteById(id);
		return true;
	}
	
}
