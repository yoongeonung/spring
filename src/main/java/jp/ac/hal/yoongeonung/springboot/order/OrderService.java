package jp.ac.hal.yoongeonung.springboot.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
