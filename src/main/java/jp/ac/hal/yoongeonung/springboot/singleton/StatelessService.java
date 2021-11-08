package jp.ac.hal.yoongeonung.springboot.singleton;

public class StatelessService {
    // 상태를 유지하지 않도록 변경
    public Integer order(String name,Integer price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
