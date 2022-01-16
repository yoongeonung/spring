package yoongeonung.springbasic.member;

/*
구현체가 하나만 있을경우 뒤에 Impl이라는 이름을 붙이는게 관례
 */
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }
}
