package tracey_hawkins.capstone.GiterDone.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tracey_hawkins.capstone.GiterDone.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    }
