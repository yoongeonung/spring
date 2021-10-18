package jp.ac.hal.yoongeonung.springboot.repository;

import jp.ac.hal.yoongeonung.springboot.domain.Member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long id);
}
