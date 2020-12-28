package jpabook.jpashop;

import org.assertj.core.api.Assertions;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

// @RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

/*
엔티티 매니저를 통한 모든 데이터 변경은 무 조 건 트랜잭션 안에서 이루어 져야함.
 */
    @Test
    // spring transcation 스기
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception {
        //given
        MemberBasic member = new MemberBasic();
        member.setUsername("memberA");

        //when
        // Ctrl alt v = extract 변수
        Long saveId = memberRepository.save(member);
        MemberBasic findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        // 같은 영속성 컨텍스트 안에 같은 식별자를 가진 애들은 true~
        Assertions.assertThat(findMember).isEqualTo(member);


    }
}