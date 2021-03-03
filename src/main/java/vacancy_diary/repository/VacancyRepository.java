package vacancy_diary.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vacancy_diary.entity.vacancy.Status;
import vacancy_diary.entity.vacancy.Vacancy;

import java.util.Date;
import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query(
            value = "SELECT v.recruiter_contacts FROM vacancies v, users u WHERE v.status = 'WAITING_FOR_FEEDBACK' AND v.user_id = u.id AND u.email = :email AND v.status_change_date < :date",
            nativeQuery = true)
    List<String> findForMessage(String email, Date date);

    Page<Vacancy> findByNameContainingIgnoreCaseAndStatusAndUserId(String name, Status status, Long userId, Pageable pageable);

    Page<Vacancy> findByNameContainingIgnoreCaseAndUserId(String name, Long userId, Pageable pageable);

}
