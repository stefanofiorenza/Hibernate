package test.orm.domain.history;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import test.orm.domain.Member;

@MappedSuperclass
public class MemberHistory extends History {

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
