package jp.ac.hal.yoongeonung.spring_mvc_practice1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemParamDTO {
    private String itemName;
    private Integer price;
    private Integer quantity;
}
