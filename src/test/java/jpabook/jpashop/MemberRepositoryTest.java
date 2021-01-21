package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;

    @Test
    //테스트에 @Transcational이 있다면 실행 후 롤백 해버림 db에 보고 싶으면 @Rollback(false)로 설정한다.
    @Transactional
    @Rollback(false)

    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);

        //then
        Member findMember = memberRepository.find(saveId);

//        assertEquals(findMember.getId(), member.getId());
//        assertEquals(findMember.getUsername(), member.getUsername());
//        assertEquals(findMember, member);

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);

        //영속성 컨테이너 1차 캐쉬에서 뽑아서 id가 같으면 같다고 인식식
       System.out.println("findMember == member :  " + (findMember == member));

    }
}