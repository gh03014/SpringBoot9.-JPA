package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    //생성자 메소드, 외부에서 MemberRepository 객체를 주입받음
    //@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*** 회원가입 ***/
    public Long join(Member member){
        validateDuplicateMember(member); //이름 중복검사
        memberRepository.save(member); //사용 가능한 이름이면 저장
        return member.getId();
    }

    //회원 이름 중복검사, Optional Member 객체 반환
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원 입니다...");
                });
    }

    /*** 전체 회원 조회 ***/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*** 회원 ID 조회 ***/
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
