package vacancy_diary.dto.request;

import lombok.Data;
import vacancy_diary.dto.request.groups.OnUpdate;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class BaseEntity implements Serializable {
    @NotNull(groups = OnUpdate.class, message = "id is require")
    private Long id;
}
