package jp.ac.hal.yoongeonung.spring_mvc_practice1.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
//        List<Item> items = new ArrayList<>();
//        store.forEach((id, item) -> items.add(item));
        // collection으로 한번 감싸서 반환
        return new ArrayList<>(store.values());
    }

    public void update(Long id, ItemParamDTO updateParam) {
        Item item = findById(id);
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }
}
