package tracey_hawkins.capstone.GiterDone.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import tracey_hawkins.capstone.GiterDone.models.Project;
import tracey_hawkins.capstone.GiterDone.models.Task;
import tracey_hawkins.capstone.GiterDone.repositories.*;
import java.util.List;
import java.util.NoSuchElementException;



@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public ProjectController(ProjectRepository projectRepository, TaskRepository taskRepository, TaskRepository todoListRepository) {
        this.projectRepository = projectRepository;

        this.taskRepository = taskRepository;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{projectId}")
    public Project getProjectById(@PathVariable Long projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new NoSuchElementException());
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PutMapping("/{projectId}")
    public Project updateProject(@PathVariable Long projectId, @RequestBody Project updatedProject) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NoSuchElementException());
        project.setName(updatedProject.getName());
        // Update other properties as needed
        return projectRepository.save(project);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NoSuchElementException());
        projectRepository.delete(project);
    }

    @PostMapping("/{projectId}/tasks")
    public void addTodoListToProject(@PathVariable Long projectId, @RequestBody Task task) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NoSuchElementException());
        project.getTodoLists().add(todoList);
        todoList.setProject(project);
        projectRepository.save(project);
        taskRepository.save(todoList);
    }

    @DeleteMapping("/{projectId}/todolists/{todoListId}")
    public void removeTodoListFromProject(@PathVariable Long projectId, @PathVariable Long todoListId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NoSuchElementException());
        TodoList todoList = todoListRepository.findById(todoListId).orElseThrow(() -> new NoSuchElementException());
        project.getTodoLists().remove(todoList);
        todoList.setProject(null);
        projectRepository.save(project);
        todoListRepository.delete(todoList);
    }
}
