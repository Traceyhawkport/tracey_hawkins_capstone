package tracey_hawkins.capstone.GiterDone.controller;


import org.springframework.web.bind.annotation.*;
import tracey_hawkins.capstone.GiterDone.models.TodoList;
import tracey_hawkins.capstone.GiterDone.models.User;
import tracey_hawkins.capstone.GiterDone.repositories.TaskDescriptionRepository;
import tracey_hawkins.capstone.GiterDone.repositories.UserRepository;


import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final TaskDescriptionRepository todoRepository;

    public UserController(UserRepository userRepository, TaskDescriptionRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }



    @PostMapping("/{userId}/todos")
    public TodoList addTodo(@PathVariable Long userId, @RequestBody TodoList todoList) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        user.getTodoList().add(todoList);
        userRepository.save(user);
        return todoList;
    }


    @PutMapping("/todos/{todoId}")
    public TodoList updateTodo(@PathVariable Long todoId, @RequestBody TodoList updatedTodo) {
        TodoList todoList = todoRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("TodoList not found"));
        todoList.setContent(updatedTodo.getContent());
        todoRepository.save(todoList);
        return todoList;
    }


    @DeleteMapping("/todos/{todoId}")
    public void deleteTodoList(@PathVariable Long todoId) {
        TodoList todoList = todoRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("TodoList not found"));
        todoRepository.delete(todoList);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        userRepository.delete(user);
    }
}