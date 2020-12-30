package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long saveId = memberService.join(member);
        //then
        // 쿼리는 날리지만 트랜잭셔널로 인해 롤백을 하기 때문에 db에서 보이지는 않지만 쿼리문은 볼 수 있음!
        em.flush();
        assertEquals(member, memberRepository.findOne(saveId));
    }
    
    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member);
        // memberService.join(member2); //예외가 발생해야 한다

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
        assertEquals("이미 존재하는 회원입니다.", exception.getMessage());



    }
}