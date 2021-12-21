package jp.ac.hal.yoongeonung.spring_mvc_practice1.domain;

import lombok.Data;

@Data
public class ItemParamDTO {
    private String itemName;
    private Integer price;
    private Integer quantity;
}
