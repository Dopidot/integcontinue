package fr.esgi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.esgi.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
