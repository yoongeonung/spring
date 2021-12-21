package jp.ac.hal.yoongeonung.spring_mvc_practice1.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Data 의 사용은 도메인에서는 피하는게 좋다.
@Getter @Setter
@NoArgsConstructor
public class Item {
    private Long id;
    private String itemName;
    private Integer price; // Integer를 사용한 이유는 null이 들어갈수 있다는 이야기, int를 쓰면 0이라도 초기화를 해줘야하기 떄문
    private Integer quantity;

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
