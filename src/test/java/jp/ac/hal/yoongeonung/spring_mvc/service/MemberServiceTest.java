package jp.ac.hal.yoongeonung.spring_mvc.service;

import jp.ac.hal.yoongeonung.spring_mvc.domain.Member;
import jp.ac.hal.yoongeonung.spring_mvc.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    private MemberService memberService;
    private MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("Test");

        //when
        Long joinedId = memberService.join(member);
        Member fMember = memberService.findOne(joinedId).get();
        //then
        assertThat(fMember.getId()).isEqualTo(joinedId);
        assertEquals(member.getName(), fMember.getName());

    }

    @Test
    void duplicateValidate() {
        //given
        Member member = new Member();
        member.setName("Test1");
        Member member1 = new Member();
        member1.setName("Test1");
        //when
        memberService.join(member);
        //then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        assertEquals("이미 존재하는 회원입니다", exception.getMessage());
    }



    @Test
    void findMembers() {
        //given
        Member member1 = new Member();
        member1.setName("Test1");
        Member member2 = new Member();
        member2.setName("Test2");
        Member member3 = new Member();
        member3.setName("Test3");
        //when
        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);
        //then
        List<Member> members = memberService.findMembers();
        assertThat(members.size()).isEqualTo(3);
    }

    @Test
    void findOne() {
        //given
        Member member1 = new Member();
        member1.setName("Test1");
        Member member2 = new Member();
        member2.setName("Test2");
        Member member3 = new Member();
        member3.setName("Test3");
        //when
        memberService.join(member1);
        Long member2Id = memberService.join(member2);
        memberService.join(member3);
        Member member = memberService.findOne(member2Id).get();
        //then
        assertThat(member).isEqualTo(member2);
        assertThat(member.getId()).isEqualTo(member2.getId());
        assertThat(member.getName()).isEqualTo(member2.getName());
    }
}