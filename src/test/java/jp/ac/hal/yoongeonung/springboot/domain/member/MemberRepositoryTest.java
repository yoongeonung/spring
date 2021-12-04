package jp.ac.hal.yoongeonung.springboot.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRepositoryTest {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @DisplayName("clear")
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("save")
    void save() {
        //given
        Member test1 = new Member("Test1", 20);
        //when
        Member savedMember = memberRepository.save(test1);
        //then
        Member foundedMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(savedMember).isEqualTo(foundedMember);
        Assertions.assertThat(savedMember).isSameAs(foundedMember);
    }

    @Test
    @DisplayName("findAll test")
    void findAll() {
        //given
        Member test1 = new Member("Test1", 20);
        Member test2 = new Member("Test2", 21);
        Member savedMember1 = memberRepository.save(test1);
        Member savedMember2 = memberRepository.save(test2);
        //when
        List<Member> members = memberRepository.findAll();
        //then
        Assertions.assertThat(members.size()).isEqualTo(2);
        Assertions.assertThat(members).contains(savedMember1, savedMember2);
    }

    @Test
    @DisplayName("clear test")
    void clearStore() {
        //given
        Member test1 = new Member("Test1", 20);
        Member test2 = new Member("Test2", 21);
        Member savedMember1 = memberRepository.save(test1);
        Member savedMember2 = memberRepository.save(test2);
        //when
        memberRepository.clearStore();
        List<Member> members = memberRepository.findAll();
        //then
        Assertions.assertThat(members.size()).isEqualTo(0);

    }
}