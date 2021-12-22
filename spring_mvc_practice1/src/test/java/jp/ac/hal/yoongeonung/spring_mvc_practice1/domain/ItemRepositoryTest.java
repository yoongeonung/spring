package jp.ac.hal.yoongeonung.spring_mvc_practice1.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    private final ItemRepository itemRepository = new ItemRepository();


    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("저장 테스트")
    void save() {
        //given
        Item testItem = new Item("Test", 3000, 100);
        //when
        Item savedItem = itemRepository.save(testItem);
        //then
        Item foundedItem = itemRepository.findById(savedItem.getId());
        assertThat(foundedItem).isEqualTo(savedItem);
    }

    @Test
    @DisplayName("모든 아이템 조회")
    void findAll() {
        //given
        Item testItem1 = new Item("Test1", 3000, 100);
        Item testItem2 = new Item("Test2", 1000, 300);
        itemRepository.save(testItem1);
        itemRepository.save(testItem2);
        //when
        List<Item> items = itemRepository.findAll();
        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(testItem1, testItem2);
    }

    @Test
    @DisplayName("아이템 갱신")
    void updateItem() {
        //given
        Item testItem1 = new Item("Test1", 3000, 100);
        Item savedItem = itemRepository.save(testItem1);
        //when
        ItemParamDTO updateItemParam = new ItemParamDTO("Test1", 2999, 100);
        itemRepository.update(testItem1.getId(), updateItemParam);
        //then
        Item foundedItem = itemRepository.findById(savedItem.getId());
        assertThat(foundedItem).isEqualTo(savedItem);
        assertThat(foundedItem.getItemName()).isEqualTo(updateItemParam.getItemName());
        assertThat(foundedItem.getPrice()).isEqualTo(updateItemParam.getPrice());
        assertThat(foundedItem.getQuantity()).isEqualTo(updateItemParam.getQuantity());
    }

}