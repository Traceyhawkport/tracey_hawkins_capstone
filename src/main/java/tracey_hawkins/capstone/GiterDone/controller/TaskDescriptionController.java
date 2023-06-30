package tracey_hawkins.capstone.GiterDone.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tracey_hawkins.capstone.GiterDone.models.TodoList;
import tracey_hawkins.capstone.GiterDone.repositories.TaskDescriptionRepository;


import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/todolists")
public class TaskDescriptionController {

    private final TaskDescriptionRepository todoListRepository;

    @Autowired
    public TaskDescriptionController(TaskDescriptionRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @GetMapping
    public List<TodoList> getAllTodoLists() {
        return todoListRepository.findAll();
    }

    @GetMapping("/{id}")
    public TodoList getTodoListById(@PathVariable Long id) {
        return todoListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TodoList not found"));
    }



    @PutMapping("/{id}")
    public TodoList updateTodoList(@PathVariable Long id, @RequestBody TodoList updatedTodoList) {
        TodoList todoList = todoListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TodoList not found"));
        todoList.setId(updatedTodoList.getId());

        return todoListRepository.save(todoList);
    }


    @DeleteMapping("/{id}")
    public void deleteTodoList(@PathVariable Long id) {
        TodoList todoList = todoListRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("TodoList not found"));
        todoListRepository.delete(todoList);
    }
}
