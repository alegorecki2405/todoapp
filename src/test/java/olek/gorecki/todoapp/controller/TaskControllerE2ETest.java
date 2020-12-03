package olek.gorecki.todoapp.controller;

import olek.gorecki.todoapp.model.Task;
import olek.gorecki.todoapp.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerE2ETest {
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    TaskRepository repo;
    @LocalServerPort
    private int port;

    @Test
    void httpGet_returnsAllTasks() {
        //given
        int initial = repo.findAll().size();
        repo.save(new Task("foo", LocalDateTime.now()));
        repo.save(new Task("bar", LocalDateTime.now()));

        //when
        Task[] result = restTemplate.getForObject("http://localhost:" + port + "/tasks", Task[].class);

        assertThat(result).hasSize(initial + 2);
    }
}