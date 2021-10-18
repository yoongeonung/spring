package jp.ac.hal.yoongeonung.springboot.service;

import jp.ac.hal.yoongeonung.springboot.domain.Member;
import jp.ac.hal.yoongeonung.springboot.repository.MemberRepository;
import jp.ac.hal.yoongeonung.springboot.repository.MemoryMemberRepository;

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
