package vacancy_diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vacancy_diary.entity.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
