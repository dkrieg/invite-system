package com.invite.process.controller;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.exception.TaskListException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static io.camunda.tasklist.dto.TaskState.CREATED;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(makeFinal = true, level = PRIVATE)
@RestController
@RequestMapping("/task-list")
public class TasklistController {
    CamundaTaskListClient client;
    String username;

    public TasklistController(CamundaTaskListClient client, @Value("${zeebe.client.security.username}") String username) {
        this.client = client;
        this.username = username;
    }

    @GetMapping("/tasks")
    @Operation(description = "get-created-tasks", summary = "Get Created Tasks")
    public ResponseEntity<List<Task>> getTasks() throws TaskListException {
        return ResponseEntity.ok(client.getTasks(false, CREATED, 50, false));
    }

    @PostMapping("/claim/{taskId}")
    @Operation(description = "claim-task", summary = "Claim Task")
    public ResponseEntity<Task> claimTask(@PathVariable("taskId") String taskId) throws TaskListException {
        client.claim(taskId, username);
        return ResponseEntity.ok(client.getTask(taskId, true));
    }

    @PostMapping("/complete/{taskId}")
    @Operation(description = "complete-task", summary = "Complete Task")
    public ResponseEntity<Task> completeTask(@PathVariable("taskId") String taskId) throws TaskListException {
        return ResponseEntity.ok(client.completeTask(taskId, Map.of()));
    }
}
