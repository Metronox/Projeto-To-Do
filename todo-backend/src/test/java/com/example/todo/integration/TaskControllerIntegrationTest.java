package com.example.todo.integration;

import com.example.todo.TodoApplication;
import com.example.todo.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TodoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void fullCrudFlow() {
        String base = "http://localhost:" + port + "/api/todos";

        Task t = new Task();
        t.setTitle("Integração");
        t.setDescription("Teste de integração");

        ResponseEntity<Task> post = restTemplate.postForEntity(base, t, Task.class);
        assertEquals(HttpStatus.CREATED, post.getStatusCode());
        assertNotNull(post.getBody().getId());

        Long id = post.getBody().getId();

        Task fetched = restTemplate.getForObject(base + "/" + id, Task.class);
        assertEquals("Integração", fetched.getTitle());

        fetched.setCompleted(true);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Task> put = new HttpEntity<>(fetched, headers);
        ResponseEntity<Task> putResp = restTemplate.exchange(base + "/" + id, HttpMethod.PUT, put, Task.class);
        assertEquals(HttpStatus.OK, putResp.getStatusCode());
        assertTrue(putResp.getBody().getCompleted());

        restTemplate.delete(base + "/" + id);

        ResponseEntity<String> afterDelete = restTemplate.getForEntity(base + "/" + id, String.class);
        assertEquals(HttpStatus.NOT_FOUND, afterDelete.getStatusCode());
    }
}
