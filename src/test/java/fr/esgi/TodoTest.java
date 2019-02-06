package fr.esgi;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.*;
import fr.esgi.dao.TodoRepository;
import fr.esgi.domain.Todo;
import fr.esgi.service.TodoService;



@RunWith(SpringJUnit4ClassRunner.class)
public class TodoTest {

	@Mock
	TodoService todoService;

	@Mock
	TodoRepository todoRepository;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void saveTodo() {
		// Given
		Todo todo = new Todo();
		todo.setMessage("test");

		// When
		OngoingStubbing<Todo> when = Mockito.when(todoService.saveTodo(Mockito.any(Todo.class)));

		// Then
		when.thenReturn(todo);
	}
	
	@Test
	public void getAllTodos() {
		// Given
		List<Todo> todos;
		
		// When
		todos = todoService.getAllTodos();

		// Then
		assertThat(todos).isNotNull();
	}
	
	@Test
	public void updateTodo() {
		// Given
		Todo todo = new Todo();
		todo.setId(1L);
		todo.setMessage("blabla");

		// When
		OngoingStubbing<Todo> when = Mockito.when(todoService.update(todo));

		// Then
		when.thenReturn(todo);
	}
	
	
	@Test
	public void findTodo() throws Exception {
		// Given
		Todo todo = new Todo();
		todo.setId(1L);
		todo.setMessage("blabla");
		long id = 1L;

		// When
		OngoingStubbing<Todo> when = Mockito.when(todoService.findTodo(id));

		// Then
		when.thenReturn(null).thenThrow(Exception.class);
	}
	
	@Test
	public void deleteTodo() {
		// Given
		long id = 1L;

		// When
		OngoingStubbing<Boolean> when = Mockito.when(todoService.deleteTodo(id));

		// Then
		when.thenReturn(false);
	}
	

}
