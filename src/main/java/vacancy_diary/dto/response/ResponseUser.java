package vacancy_diary.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vacancy_diary.entity.user.User;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseUser extends BaseEntity{
    private String email;

    public ResponseUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
    }
}
