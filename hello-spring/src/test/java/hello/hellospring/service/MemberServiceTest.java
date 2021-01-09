package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void  beforeEach() {
        //MemoryMemberRepository 객체를 MemberService의
        //생성자 메소드에 주입하여 MemberServoce 객체 생성
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    /*** 회원가입 테스트 ***/
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("정재환");

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}