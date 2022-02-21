package yoongeonung.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
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
        // store에 있는 모든값들을 꺼내서 새로운 리스트를 생성해서 반환
        // store 보호차원에서 한번 감싸서 보낸다.
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
