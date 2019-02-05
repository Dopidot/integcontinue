package fr.esgi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.domain.Todo;
import fr.esgi.service.TodoService;

@RequestMapping(path = "/api")
@RestController
public class TodoRessouce {
	@Autowired
	private TodoService todoService;

	@GetMapping(value = "/todos")
	public List<Todo> getAllTodos() {
		return todoService.getAllTodos();
	}

	@PostMapping(value = "/todos")
	public Todo createTodo(@RequestBody Todo todo) {
		return todoService.saveTodo(todo);
	}

	@PutMapping(value = "/todos")
	public Todo updateTodo(@RequestBody Todo todo) {
		return todoService.update(todo);
	}

	@GetMapping(value = "/todos/{id}")
	public Todo findTodo(@PathVariable Long id) throws Exception {
		return todoService.findTodo(id);
	}

	@DeleteMapping(value = "/todos/{id}")
	public void deleteTodo(@PathVariable Long id) {
		todoService.deleteTodo(id);
	}

}
