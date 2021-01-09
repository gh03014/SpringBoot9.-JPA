package hello.hellospring;

import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration //자바 코드로 스프링 빈 등록
public class SpringConfig {
    private final EntityManager em;

    public SpringConfig(EntityManager em){
        this.em = em;
    }

    //private DataSource dataSource;

    //@Autowired
    //public SpringConfig(DataSource dataSource){
    //    this.dataSource = dataSource;
    //}
    
    @Bean //bean 객체 생성, 등록
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
