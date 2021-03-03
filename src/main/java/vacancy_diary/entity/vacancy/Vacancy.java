package vacancy_diary.entity.vacancy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import vacancy_diary.entity.BaseEntity;
import vacancy_diary.entity.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vacancies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Column(name = "expected_salary")
    private Integer expectedSalary;

    @Column(name = "vacancy_link")
    private String vacancyLink;

    @Column(name = "recruiter_contacts")
    private String recruiterContacts;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "status_change_date")
    private Date statusChangeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
