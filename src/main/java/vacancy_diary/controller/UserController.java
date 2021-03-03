package vacancy_diary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vacancy_diary.dto.request.RequestUser;
import vacancy_diary.dto.request.groups.OnCreate;
import vacancy_diary.dto.request.groups.OnUpdate;
import vacancy_diary.dto.response.ResponseUser;
import vacancy_diary.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("registration")
    @Validated(OnCreate.class)
    public ResponseEntity<ResponseUser> create(@RequestBody @Valid RequestUser user) {
        return ResponseEntity.ok(service.create(user));
    }

    @PostMapping("login")
    @Validated(OnCreate.class)
    public ResponseEntity<Map<Object, Object>> authenticate(@RequestBody @Valid RequestUser user) {
        return ResponseEntity.ok(service.authenticate(user.getEmail(), user.getPassword()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseUser> read(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.read(id));
    }

    @PutMapping()
    @Validated(OnUpdate.class)
    public ResponseEntity<ResponseUser> update(@RequestBody @Valid RequestUser user) {
        return ResponseEntity.ok(service.update(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
