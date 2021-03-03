package vacancy_diary.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vacancy_diary.entity.vacancy.Status;
import vacancy_diary.entity.vacancy.Vacancy;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseVacancy extends BaseEntity{
    private String name;
    private String position;
    private Integer expectedSalary;
    private String vacancyLink;
    private String recruiterContacts;
    private Status status;
    private Date statusChangeDate;

    public ResponseVacancy(Vacancy vacancy) {
        this.id = vacancy.getId();
        this.name = vacancy.getName();
        this.position = vacancy.getPosition();
        this.expectedSalary = vacancy.getExpectedSalary();
        this.vacancyLink = vacancy.getVacancyLink();
        this.recruiterContacts = vacancy.getRecruiterContacts();
        this.status = vacancy.getStatus();
        this.statusChangeDate = vacancy.getStatusChangeDate();
    }
}
