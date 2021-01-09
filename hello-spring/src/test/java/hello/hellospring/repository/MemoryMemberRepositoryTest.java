package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {
    //객체 생성 메모리 할당
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test  //리포지토리에 도메인이 저장이 완료되는지 테스트
    public void save() {
        //회원 도메인 생성, 값 setter
        Member member = new Member();
        member.setName("정재환");

        repository.save(member); //생성된 도메인을 리포지토리에 저장

        //리포지토리에 저장된 값을 읽어와서 실제 도메인과 값 비교
        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("정재환1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("정재환2");
        repository.save(member2);

        Member result = repository.findByName("정재환1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("정재환1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("정재환2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
