package fr.esgi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.esgi.dto.TodoDTO;
import fr.esgi.exception.TodoException;

@Service
public interface TodoService {

	/**
	 * Get all the todos.
	 *
	 * @return the list of entities
	 */
	List<TodoDTO> findAll();

	/**
	 * Save a todo.
	 *
	 * @param todoDTO the entity to save
	 * @return the persisted entity
	 */
	TodoDTO save(TodoDTO todoDTO);

	/**
	 * Update a todo.
	 *
	 * @param todoDTO the entity to save
	 * @return the persisted entity
	 */
	TodoDTO update(TodoDTO todoDTO);

	/**
	 * Get the "id" todo.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	TodoDTO findOne(Long id) throws TodoException;

	/**
	 * Delete the "id" todo.
	 *
	 * @param id the id of the entity
	 */
	void deleteTodo(Long id);
}
