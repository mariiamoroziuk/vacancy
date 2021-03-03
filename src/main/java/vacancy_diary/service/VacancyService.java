package vacancy_diary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vacancy_diary.dto.request.RequestVacancy;
import vacancy_diary.dto.response.ResponseVacancy;
import vacancy_diary.entity.user.User;
import vacancy_diary.entity.vacancy.Status;
import vacancy_diary.entity.vacancy.Vacancy;
import vacancy_diary.exception.NoDataFoundException;
import vacancy_diary.repository.VacancyRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final UserService userService;
    private final MessageService messageService;

    public ResponseVacancy create(RequestVacancy vacancy, String email) {
        Vacancy v = vacancy.toEntity(userService.findByEmail(email));
        return new ResponseVacancy(vacancyRepository.save(v));
    }

    public ResponseVacancy read(Long id) {
        return vacancyRepository.findById(id)
                .map(ResponseVacancy::new)
                .orElseThrow(() -> new NoDataFoundException("vacancy", id));
    }

    public List<ResponseVacancy> readAll(int page, int size, User user, String name, Optional<Status> optStatus) {
        Page<Vacancy> vacancies = optStatus
                .map(status -> vacancyRepository.findByNameContainingIgnoreCaseAndStatusAndUserId(name, status, user.getId(), PageRequest.of(page, size)))
                .orElse(vacancyRepository.findByNameContainingIgnoreCaseAndUserId(name, user.getId(), PageRequest.of(page, size)));

        return vacancies.getContent()
                .stream()
                .map(ResponseVacancy::new)
                .collect(Collectors.toList());
    }

    public ResponseVacancy update(RequestVacancy vacancy) {
        return vacancyRepository.findById(vacancy.getId())
                .map(found -> {
                    found.setName(vacancy.getName());
                    found.setPosition(vacancy.getPosition());
                    found.setExpectedSalary(vacancy.getExpectedSalary());
                    found.setRecruiterContacts(vacancy.getRecruiterContacts());
                    if (vacancy.getStatus() != found.getStatus()) {
                        found.setStatus(vacancy.getStatus());
                        found.setStatusChangeDate(new Date());
                    }
                    return vacancyRepository.save(found);
                })
                .map(ResponseVacancy::new)
                .orElseThrow(() -> new NoDataFoundException("vacancy", vacancy.getId()));
    }

    public int sendMessages(String email) {
        String text = "<p>hello, I am waiting for feedback from you, I will be grateful for your answer</p>" + email;
        Date now = new Date();
        LocalDateTime dateTime = LocalDateTime.now().minusDays(7);
        Date date = java.sql.Timestamp.valueOf(dateTime);

        List<String> contacts = vacancyRepository.findForMessage(email, date);
        contacts.parallelStream().forEach(contact -> messageService.send(contact, "candidate letter", text, email));

        return contacts.size();
    }
}
