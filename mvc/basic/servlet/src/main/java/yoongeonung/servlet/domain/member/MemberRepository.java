package yoongeonung.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberRepository {

  private static final Map<Long, Member> store = new HashMap<>();
  private static Long sequence = 0L;

  private static final MemberRepository instance = new MemberRepository();

  public static MemberRepository getInstance() {
    return instance;
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
    // store 보호를 위해 한번 감싸서 반환
    return new ArrayList<>(store.values());
  }

  public void clearStore() {
    store.clear();
  }

}
