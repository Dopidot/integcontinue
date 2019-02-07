package fr.esgi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import fr.esgi.dao.TodoRepository;
import fr.esgi.domain.Todo;
import fr.esgi.dto.TodoDTO;
import fr.esgi.mapper.TodoMapper;
import fr.esgi.web.TodoRessource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoApplication.class)
public class TodoTests {

	private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";

	private static final String UPDATED_MESSAGE = "efe";

	@Autowired
	private TodoRessource todoRessouce;

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private TodoMapper todoMapper;

	private Todo todo;

	private MockMvc restTodoMockMvc;

	@Autowired
	private EntityManager em;


	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		this.restTodoMockMvc = MockMvcBuilders.standaloneSetup(todoRessouce)
				.build();
	}

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it,
	 * if they test an entity which requires the current entity.
	 */
	public static Todo createEntity(EntityManager em) {
		Todo todo = new Todo();
		todo.setMessage(DEFAULT_MESSAGE);
		return todo;
	}

	@Before
	public void initTest() {
		todo = createEntity(em);
	}

	@Test
	@Transactional
	public void createTodo() throws Exception {
		int databaseSizeBeforeCreate = todoRepository.findAll().size();

		// Create the Classroom
		TodoDTO todoDTO = todoMapper.todoToTodoDTO(todo);
		restTodoMockMvc.perform(post("/api/todos")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(todoDTO)))
		.andExpect(status().isCreated());

		// Validate the Todo in the database
		List<Todo> todoList = todoRepository.findAll();
		assertThat(todoList).hasSize(databaseSizeBeforeCreate + 1);
		Todo testTodo = todoList.get(todoList.size() - 1);
		assertThat(testTodo.getMessage()).isEqualTo(DEFAULT_MESSAGE);
	}

	@Test
	@Transactional
	public void getAllTodos() throws Exception {
		// Initialize the database
		todoRepository.saveAndFlush(todo);

		// Get all the todoList
		restTodoMockMvc.perform(get("/api/todos"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.[*].id").value(hasItem(todo.getId().intValue())))
		.andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)));
	}

	@Test
	@Transactional
	public void getTodo() throws Exception {
		// Initialize the database
		todoRepository.saveAndFlush(todo);

		// Get the todo
		restTodoMockMvc.perform(get("/api/todos/{id}", todo.getId()))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(jsonPath("$.id").value(todo.getId().intValue()))
		.andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE));
	}

	@Test
	@Transactional
	public void updateTodo() throws Exception {
		// Initialize the database
		todoRepository.saveAndFlush(todo);
		int databaseSizeBeforeUpdate = todoRepository.findAll().size();

		// Update the todo
		Long id = todo.getId();
		Optional<Todo> todo = todoRepository.findById(id);
		// Disconnect from session so that the updates on updatedClassroom are not directly saved in db
		em.detach(todo.get());
		todo.get()
		.setMessage("efe");
		TodoDTO todoDTO = todoMapper.todoToTodoDTO(todo.get());

		restTodoMockMvc.perform(put("/api/todos")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(todoDTO)))
		.andExpect(status().isOk());

		// Validate the Classroom in the database
		List<Todo> classroomList = todoRepository.findAll();
		assertThat(classroomList).hasSize(databaseSizeBeforeUpdate);
		Todo testTodo = classroomList.get(classroomList.size() - 1);
		assertThat(testTodo.getMessage()).isEqualTo(UPDATED_MESSAGE);
	}

	@Test
	@Transactional
	public void deleteTodo() throws Exception {
		// Initialize the database
		todoRepository.saveAndFlush(todo);
		int databaseSizeBeforeDelete = todoRepository.findAll().size();

		// Get the classroom
		restTodoMockMvc.perform(delete("/api/todos/{id}", todo.getId())
				.accept(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk());

		// Validate the database is empty
		List<Todo> todoList = todoRepository.findAll();
		assertThat(todoList).hasSize(databaseSizeBeforeDelete - 1);
	}

}
