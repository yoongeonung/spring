package user.template;

public interface LineCallback<T> {
//    Integer doSomethingWithLine(String line, Integer val);
    // 제니릭스 적용
    T doSomethingWithLine(String line, T val);
}
