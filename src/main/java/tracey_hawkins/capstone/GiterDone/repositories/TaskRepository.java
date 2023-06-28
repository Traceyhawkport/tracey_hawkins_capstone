package tracey_hawkins.capstone.GiterDone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tracey_hawkins.capstone.GiterDone.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Add custom query methods if needed
}
