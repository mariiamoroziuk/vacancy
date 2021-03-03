package vacancy_diary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vacancy_diary.dto.request.RequestVacancy;
import vacancy_diary.dto.request.groups.OnCreate;
import vacancy_diary.dto.request.groups.OnUpdate;
import vacancy_diary.dto.response.ResponseVacancy;
import vacancy_diary.entity.vacancy.Status;
import vacancy_diary.service.UserService;
import vacancy_diary.service.VacancyService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("api/v1/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    private final UserService userService;

    @PostMapping
    @Validated(OnCreate.class)
    public ResponseEntity<ResponseVacancy> create(@RequestBody @Valid RequestVacancy vacancy, Principal principal) {
        return ResponseEntity.ok(vacancyService.create(vacancy, principal.getName()));
    }

    @GetMapping
    public ResponseEntity<List<ResponseVacancy>> readAll(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size,
                                                         @RequestParam(defaultValue = "") String name,
                                                         @RequestParam Optional<Status> status,
                                                         Principal principal) {
        return ResponseEntity.ok(vacancyService.readAll(page, size, userService.findByEmail(principal.getName()), name, status));
    }

    @PutMapping()
    @Validated(OnUpdate.class)
    public ResponseEntity<ResponseVacancy> update(@RequestBody @Valid RequestVacancy vacancy) {
        return ResponseEntity.ok(vacancyService.update(vacancy));
    }

    @GetMapping("/sendMessages")
    public ResponseEntity<Integer> sendMessages(Principal principal) {
        return ResponseEntity.ok(vacancyService.sendMessages(principal.getName()));
    }
}
