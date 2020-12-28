package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    // 기본 컴포넌트

    // 스프링 부트가 엔티티매니저를 주입해 줌
    //@PersistenceContext
    //private EntityManager em;

    public Long save(MemberBasic member) {
        //em.persist(member);
        return member.getId();
    }
    /*
        member를 안가져오고 왜 getId를 가져올까?
        : command 와 query를 분리하라는 원칙에 의해.
        날리는 쿼리가 커맨드성이기 때문에 리턴값을 잘 안만들지만 (사이드이펙트를 일으킬 수 있음 )
        id정도 있으면 다음에 다시 조회할 수 있으니까 이정도만 남겨둠
     */

    // shift+ctrl+t = test
    public MemberBasic find(Long id) {
        //return em.find(Member.class, id);
        return null;
    }

}
