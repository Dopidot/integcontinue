package fr.esgi.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.esgi.dao.TodoRepository;
import fr.esgi.domain.Todo;
import fr.esgi.dto.TodoDTO;
import fr.esgi.exception.TodoException;
import fr.esgi.mapper.TodoMapper;
import fr.esgi.service.TodoService;


/**
 * Service Implementation for managing Todo.
 */
@Service("TodoService")
@Transactional
public class TodoServiceImpl implements TodoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodoServiceImpl.class);

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private TodoMapper todoMapper;

	/**
	 * Get all the todos.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<TodoDTO> findAll() {
		return todoRepository.findAll().stream()
				.map(todoMapper::todoToTodoDTO)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Save a todo.
	 *
	 * @param todoDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public TodoDTO save(TodoDTO todoDTO) {
		Todo todo = todoMapper.todoDTOToTodo(todoDTO);
		todo = todoRepository.save(todo);
		return todoMapper.todoToTodoDTO(todo);
	}

	/**
	 * Update a todo.
	 *
	 * @param todoDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public TodoDTO update(TodoDTO todoDTO) {
		Todo todo = todoMapper.todoDTOToTodo(todoDTO);
		todo = todoRepository.saveAndFlush(todo);
		return todoMapper.todoToTodoDTO(todo);
	}

	/**
	 * Get the "id" todo.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	public TodoDTO findOne(Long id) throws TodoException {
		Optional<Todo> todo = todoRepository.findById(id);

		if (todo.isPresent()) {
			return todoMapper.todoToTodoDTO(todo.get());
		} else {
			LOGGER.error("Error todo with id {} does not exists", id);
			throw new TodoException("Error todo with id does not exists");
		}
	}

	/**
	 * Delete the "id" todo.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void deleteTodo(Long id) {
		todoRepository.deleteById(id);
	}

}
