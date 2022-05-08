package yoongeonung.servlet.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

  private final MemberRepository memberRepository = MemberRepository.getInstance();

  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void save() {
    //given
    Member member1 = new Member("member1", 10);

    //when
    Member savedMember = memberRepository.save(member1);

    //then
    Member findMember = memberRepository.findById(member1.getId());
    assertThat(savedMember).isEqualTo(findMember);
  }

  @Test
  void findALl() {
    //given
    Member member1 = new Member("member1", 10);
    Member member2 = new Member("member2", 20);
    Member member3 = new Member("member3", 30);

    memberRepository.save(member1);
    memberRepository.save(member2);
    memberRepository.save(member3);

    //when
    List<Member> members = memberRepository.findAll();

    //then
    assertThat(members.size()).isEqualTo(3);
    assertThat(members).contains(member1, member2, member3);

  }

}