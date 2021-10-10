package jp.ac.hal.yoongeonung.spring_mvc.repository;

import jp.ac.hal.yoongeonung.spring_mvc.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    private MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member();
        member.setName("TestUser1");
        memberRepository.save(member);
        //when
        // Optional을 get()으로 바로 가져오는건 안좋으나 Test케이스니까 OK
        Member foundMember = memberRepository.findById(member.getId()).get();
        //then
        Assertions.assertThat(member).isEqualTo(foundMember);
    }

    @Test
    void findById() {
        //given
        Member member = new Member();
        member.setName("TestUser1");
        memberRepository.save(member);
        //when
        Member member1 = memberRepository.findById(member.getId()).get();
        //then
        Assertions.assertThat(member).isEqualTo(member1);
    }

    @Test
    void findByName() {
        //given
        Member member = new Member();
        member.setName("TestUser1");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("TestUser2");
        memberRepository.save(member2);
        //when
        Member testUser1 = memberRepository.findByName("TestUser1").get();
        //then
        Assertions.assertThat(member).isEqualTo(testUser1);

    }

    @Test
    void findAll() {
        //given
        Member member = new Member();
        member.setName("TestUser1");
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("TestUser2");
        memberRepository.save(member2);
        //whne
        List<Member> members = memberRepository.findAll();
        //then
        Assertions.assertThat(members.size()).isEqualTo(2);
    }
}