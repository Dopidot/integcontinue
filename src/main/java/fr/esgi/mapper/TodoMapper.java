package fr.esgi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import fr.esgi.domain.Todo;
import fr.esgi.dto.TodoDTO;
@Mapper(uses = { TodoDTO.class }, componentModel = "spring")
public interface TodoMapper {

	@Mappings({
		@Mapping(source = "id", target = "id"),
		@Mapping(source = "message", target = "message")
	})
	Todo todoDTOToTodo(TodoDTO todoDTO);
	
	@InheritInverseConfiguration
	TodoDTO todoToTodoDTO(Todo todo);
	
}
