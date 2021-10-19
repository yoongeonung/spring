package jp.ac.hal.yoongeonung.springboot.member;

import jp.ac.hal.yoongeonung.springboot.member.Member;

public interface MemberRepository {
    void save(Member member);

    void clearAll();
    Member findById(Long id);
}
