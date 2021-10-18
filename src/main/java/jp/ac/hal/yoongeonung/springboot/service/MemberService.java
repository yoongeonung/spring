package jp.ac.hal.yoongeonung.springboot.service;

import jp.ac.hal.yoongeonung.springboot.domain.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long id);
}
