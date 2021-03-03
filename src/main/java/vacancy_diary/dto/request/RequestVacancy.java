package vacancy_diary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vacancy_diary.dto.request.groups.OnCreate;
import vacancy_diary.dto.request.groups.OnUpdate;
import vacancy_diary.entity.user.User;
import vacancy_diary.entity.vacancy.Status;
import vacancy_diary.entity.vacancy.Vacancy;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class RequestVacancy extends BaseEntity {
    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "vacancy name is require")
    private String name;

    private String position;
    private Integer expectedSalary;
    private String vacancyLink;
    private String recruiterContacts;
    private Status status;

    public Vacancy toEntity(User user) {
        return new Vacancy(
                this.name, this.position, this.expectedSalary, this.vacancyLink, this.recruiterContacts, this.status, new Date(), user
        );
    }
}
