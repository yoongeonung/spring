package yoongeonung.itemservice.domain.item;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

  private static final Map<Long, Item> store = new HashMap<>(); // 동시성 문제 고려해서 ConcurrentHashMap을 사용해야 한다.
  private static final long SEQUENCE = 0L; // 동시성 문제 고려서 AtomicLong 사용해야 한다.



}
