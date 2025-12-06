package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskService service;

    @Test
    public void listLatest() throws Exception {
        Task t = new Task();
        t.setId(1L);
        t.setTitle("hello");
        Mockito.when(service.latest(5)).thenReturn(List.of(t));

        mvc.perform(get("/api/tasks")).andExpect(status().isOk()).andExpect(jsonPath("$[0].title", is("hello")));
    }

    @Test
    public void createTask() throws Exception {
        Task in = new Task();
        in.setTitle("t1");
        in.setDescription("d");
        Task out = new Task();
        out.setId(10L);
        out.setTitle("t1");
        Mockito.when(service.create(Mockito.any())).thenReturn(out);

        mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON).content("{\"title\":\"t1\",\"description\":\"d\"}"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(10)));
    }
}
