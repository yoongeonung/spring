package jp.ac.hal.yoongeonung.springboot.repository;

import jp.ac.hal.yoongeonung.springboot.domain.Member;

public interface MemberRepository {
    void save(Member member);

    void clearAll();
    Member findById(Long id);
}
