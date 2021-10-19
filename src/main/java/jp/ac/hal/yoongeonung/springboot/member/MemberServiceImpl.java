package jp.ac.hal.yoongeonung.springboot.member;

public class MemberServiceImpl implements MemberService{

    //
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public void clearAll() {
        memberRepository.clearAll();
    }
}
