package tracey_hawkins.capstone.GiterDone.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tracey_hawkins.capstone.GiterDone.models.Task;
import tracey_hawkins.capstone.GiterDone.models.*;
import tracey_hawkins.capstone.GiterDone.repositories.TaskRepository;
import tracey_hawkins.capstone.GiterDone.repositories.TaskDescriptionRepository;


import java.util.NoSuchElementException;

@Controller
@RequestMapping("/todos/{todoId}/tasks")
public class TaskController {

    private final TaskDescriptionRepository todoListRepository;
    private final TaskRepository taskRepository;

    public TaskController(TaskDescriptionRepository taskDescriptionRepository, TaskRepository taskRepository) {
        this.todoListRepository] = todoListRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/{taskId}")
    public Task getTaskById(@PathVariable Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
    }

    @PostMapping
    public Task addTask(@PathVariable Long todoId, @RequestBody Task task) {
        TodoList todoList = todoListRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("TodoList not found"));
        ((TodoList) todoList).getTasks().add(task);
        task.setTodoList(todoList);
        return taskRepository.save(task);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
        task.setContent(updatedTask.getContent());
        return taskRepository.save(task);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NoSuchElementException("Task not found"));
        taskRepository.delete(task);
    }
}