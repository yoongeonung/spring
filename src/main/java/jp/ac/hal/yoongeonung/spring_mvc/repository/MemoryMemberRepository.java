package jp.ac.hal.yoongeonung.spring_mvc.repository;

import jp.ac.hal.yoongeonung.spring_mvc.domain.Member;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryMemberRepository implements MemberRepository {
    // 동시성 문제를 고려해 ConcurrentHashMap 사용하는게 좋다
    private static final Map<Long, Member> store = new HashMap<>();
    // 동시성 문제를 고려해 AtomicLong 사용하는게 좋다
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null로 반환될 가능성이 있을경우 Optional로 감싸서 반환해준다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
