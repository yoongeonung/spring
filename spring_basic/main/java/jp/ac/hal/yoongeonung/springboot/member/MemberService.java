package jp.ac.hal.yoongeonung.springboot.member;

import jp.ac.hal.yoongeonung.springboot.member.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long id);

    void clearAll();
}
