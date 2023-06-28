package tracey_hawkins.capstone.GiterDone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tracey_hawkins.capstone.GiterDone.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Add custom query methods if needed
}
