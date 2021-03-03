package vacancy_diary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import vacancy_diary.dto.request.RequestUser;
import vacancy_diary.dto.response.ResponseUser;
import vacancy_diary.entity.user.User;
import vacancy_diary.exception.NoDataFoundException;
import vacancy_diary.repository.UserRepository;
import vacancy_diary.security.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseUser create(RequestUser user) {
        return new ResponseUser(repository.save(user.toEntity()));
    }

    public ResponseUser read(Long id) {
        return repository.findById(id).map(ResponseUser::new).orElseThrow(() -> new NoDataFoundException("user", id));
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NoDataFoundException(String.format("not found user with email %s", email)));
    }

    public ResponseUser update(RequestUser user) {
        return repository.findById(user.getId())
                .map(found -> {
                    found.setEmail(user.getEmail());
                    found.setPassword(user.getPassword());
                    return repository.save(found);
                })
                .map(ResponseUser::new)
                .orElseThrow(() -> new NoDataFoundException("user", user.getId()));
    }

    public Long delete(Long id) {
        repository.deleteById(id);
        return id;
    }

    public Map<Object, Object> authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        User user = repository.findByEmail(email).orElseThrow(() -> new NoDataFoundException("User doesn't exists"));

        String token = jwtTokenProvider.createToken(user.getEmail());

        Map<Object, Object> tokens = new HashMap<>();
        tokens.put("userId", user.getId());
        tokens.put("token", "Bearer " + token);
        return tokens;
    }
}
