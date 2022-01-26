package yoongeonung.springbasic.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
구현체가 하나만 있을경우 뒤에 Impl이라는 이름을 붙이는게 관례
 */
@Component
public class MemberServiceImpl implements MemberService{

    /**
     * OCP, DIP 위반
     * 대신 주입해줄 제3자가 필요하다
     */
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * OCP, DIP 준수
     */
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }


    // only for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
