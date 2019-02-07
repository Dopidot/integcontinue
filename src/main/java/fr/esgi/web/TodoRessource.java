package fr.esgi.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zalando.problem.Status;

import fr.esgi.dto.TodoDTO;
import fr.esgi.exception.StatusAlertException;
import fr.esgi.exception.TodoException;
import fr.esgi.service.TodoService;

@RestController
@RequestMapping(path = "/api")
public class TodoRessource {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodoRessource.class);
	
    private static final String ENTITY_NAME = "todo";

    @Autowired
	private TodoService todoService;


	/**
	 * GET  /todos : get all Todos.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of Todos in body
	 */
	@GetMapping(value = "/todos")
	public List<TodoDTO> getAllTodos() {
		LOGGER.debug("REST request to get all Todos");
		return todoService.findAll();
	}

	/**
	 * POST  /todos : create Todo.
	 *
	 * @return the ResponseEntity with status 200 (OK) and Todo in body
	 * @throws TodoException 
	 */
	@PostMapping(value = "/todos")
	public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDTO) {
		LOGGER.debug("REST request to create Todo : {}", todoDTO);
		if (todoDTO.getId() != null) {
			   throw new StatusAlertException(
					   Status.BAD_REQUEST, "A new todo cannot already have an ID", ENTITY_NAME, "idexists"
					   );
        }
		return new ResponseEntity<>(todoService.save(todoDTO), HttpStatus.CREATED);
	}

	/**
	 * PUT  /todos : update Todo.
	 *
	 * @return the ResponseEntity with status 200 (OK) and Todo in body
	 */
	@PutMapping(value = "/todos")
	public TodoDTO updateTodo(@RequestBody TodoDTO todoDTO) {
		LOGGER.debug("REST request to update Todo : {}", todoDTO);
		return todoService.update(todoDTO);
	}

	/**
	 * GET  /todos : get Todo.
	 *
	 * @return the ResponseEntity with status 200 (OK) and with body the Todo, or with status 404 (Not Found)
	 */
	@GetMapping(value = "/todos/{id}")
	public TodoDTO getTodo(@PathVariable Long id) throws TodoException {
		LOGGER.debug("REST request to get Todo : {}", id);
		return todoService.findOne(id);
	}

	/**
	 * DELETE  /todos : delete Todo.
	 *
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping(value = "/todos/{id}")
	public void deleteTodo(@PathVariable Long id) {
		LOGGER.debug("REST request to delete a Todo : {}", id);
		todoService.deleteTodo(id);
	}

}
