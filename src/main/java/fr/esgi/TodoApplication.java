package fr.esgi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.esgi.dao.TodoRepository;
import fr.esgi.domain.Todo;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner{

	@Autowired
	private TodoRepository todoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Todo todo = new Todo();
		todo.setMessage("Frites");
		todoRepository.save(todo);
		
		todo = new Todo();
		todo.setMessage("Viande rouge");
		todoRepository.save(todo);
		
	}

}

