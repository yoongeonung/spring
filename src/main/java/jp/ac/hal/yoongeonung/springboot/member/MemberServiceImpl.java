package jp.ac.hal.yoongeonung.springboot.member;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemberServiceImpl implements MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private MemberRepository memberRepository;

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

    @Override
    public void clearAll() {
        memberRepository.clearAll();
    }

    //for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
