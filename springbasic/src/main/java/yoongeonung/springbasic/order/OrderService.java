package yoongeonung.springbasic.order;

public interface OrderService {
    /**
     * @return Order를 생성후 반환
     */
    Order createOrder(Long id, String itemName, int itemPrice);
}
