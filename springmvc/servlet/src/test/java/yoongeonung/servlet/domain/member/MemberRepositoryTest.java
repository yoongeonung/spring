package yoongeonung.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    @DisplayName("저장")
    void save() {
        Member member = new Member();
        member.setAge(20);
        member.setUsername("Kakao");
        Member member1 = memberRepository.save(member);

        Member member2 = memberRepository.findById(member1.getId());

        assertThat(member1).isInstanceOf(Member.class);
        assertThat(member1.getId()).isEqualTo(member2.getId());
        assertThat(member1.getUsername()).isEqualTo(member.getUsername());
    }

    @Test
    @DisplayName("모두찾기")
    void findAll() {
        Member member1 = new Member();
        member1.setUsername("KAKAO");
        member1.setAge(20);
        Member member2 = new Member();
        member2.setUsername("LINE");
        member2.setAge(20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(2);
        assertThat(members).contains(member1, member2);
    }
}