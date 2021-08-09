package test.orm.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.orm.domain.base.AbstractMemberAuditableEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "scheduled_training")
public class ScheduledTraining extends AbstractMemberAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member recipient;

    @Column(name = "completed_on")
    private LocalDateTime completedOn;

    @Column(name = "deadline")
    private LocalDateTime deadline;

}
