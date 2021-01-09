package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //테스츠시 다시 rollback하여 원상복귀 한다
class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    /*** 회원가입 테스트 ***/
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("정재환4");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    /*** 중복검사 기능 테스트, 예외가 발생해야 테스트 성공 ***/
    @Test
    void 중복회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("정재환");

        Member member2 = new Member();
        member2.setName("정재환");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        /*
        try{
            memberService.join(member2);
            fail(); //회원가입이 되면 테스츠 실패
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다...");
        }
        */
        //then
    }
}