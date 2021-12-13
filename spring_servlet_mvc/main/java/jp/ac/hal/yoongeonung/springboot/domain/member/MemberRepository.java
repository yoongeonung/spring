package jp.ac.hal.yoongeonung.springboot.domain.member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    private static class MemberRepositoryHolder {
        private static final MemberRepository INSTANCE = new MemberRepository();

    }

    public static MemberRepository getInstance() {
        return MemberRepositoryHolder.INSTANCE;
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
