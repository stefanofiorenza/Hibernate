package test.orm.domain;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.orm.domain.ems.Employee;
import test.orm.domain.history.RoleHistoryRecord;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="\"member\"") //some Db have member as sql function
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            //TODO: add index

    @Column(name = "onboard_date", nullable = false)
    private LocalDateTime onBoardDate;

    @Column(name = "offboard_date", nullable = false)
    private LocalDateTime offBoardDate;

    @OneToOne(optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
  
    @OneToMany(mappedBy = "createdBy")
    private Set<Practice> practices;

    @OneToMany(mappedBy = "createdBy")
    private Set<Email> emails;

    @OneToMany(mappedBy = "createdBy")
    private Set<EmailTemplate> emailTemplates;

    @OneToMany(mappedBy = "createdBy")
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "createdBy")
    private Set<GroupMembership> groupMemberships;

    @OneToMany(mappedBy = "createdBy")
    private Set<LearningSubscription> memberTrainingPaths;

    @OneToMany(mappedBy = "createdBy")
    private Set<ScheduledTraining> memberTrainings;

    @OneToMany(mappedBy = "member")
    private Set<RoleHistoryRecord> businessUnitHistoryRecords;




}
