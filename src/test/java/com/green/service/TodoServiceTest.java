package com.green.service;

import com.green.jdbcex.dto.TodoDTO;
import com.green.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@Log4j2
public class TodoServiceTest {
    private TodoService todoService;

          @BeforeEach
        public void ready() {
            todoService = TodoService.INSTANCE;
        }

    @Test
    public void testRegister()throws Exception {

        TodoDTO todoDTO = TodoDTO.builder()
                .title("JDBC DTO Test")
                .dueDate(LocalDate.now())
                .build();

        System.out.println("todoDTO "+todoDTO);
        log.info("todoDTO# "+todoDTO);
        todoService.register(todoDTO);
    }




}
