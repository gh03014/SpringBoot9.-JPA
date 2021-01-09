package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
    //값을 저장할 HashMap 객체 생성
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //시퀀스 자동 생성

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //시퀀스 값 저장
        store.put(member.getId(), member); //값을 불러와서 store에 저장
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //값이 null이여도 처리할 수 있도록 ofNullable()을 적용
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //filter를 이용해 member에 저장된 값과 파라미터 변수 값이 같은지 확인
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() { //list 객체로 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
