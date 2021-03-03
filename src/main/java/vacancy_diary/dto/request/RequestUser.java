package vacancy_diary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vacancy_diary.dto.request.groups.OnCreate;
import vacancy_diary.dto.request.groups.OnUpdate;
import vacancy_diary.entity.user.User;
import vacancy_diary.entity.vacancy.Vacancy;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class RequestUser extends BaseEntity {
    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "user email is require")
    @Email(groups = {OnCreate.class, OnUpdate.class}, message = "not email")
    private String email;

    @NotBlank(groups = OnCreate.class, message = "user password is require")
    @Size(groups = OnCreate.class, min = 6, message = "user password is too short")
    private String password;

    public User toEntity() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        return new User(this.email, bCryptPasswordEncoder.encode(this.password), new ArrayList<>());
    }
}
