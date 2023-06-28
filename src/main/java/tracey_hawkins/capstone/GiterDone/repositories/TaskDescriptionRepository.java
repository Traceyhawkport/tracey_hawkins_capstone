package tracey_hawkins.capstone.GiterDone.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tracey_hawkins.capstone.GiterDone.models.TaskDescription;

@Repository
public interface TaskDescriptionRepository extends JpaRepository<TaskDescription, Long> {
    // Add custom query methods if needed
}