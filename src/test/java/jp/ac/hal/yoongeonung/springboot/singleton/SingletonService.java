package jp.ac.hal.yoongeonung.springboot.singleton;

public class SingletonService {

    /**
     * 싱글톤 패턴
     * 자기 자신을 private final static 으로 선언
     * 자바애플리케이션이 뜨면서 자기자신을 생성해서 instance 참조변수에 넣어둔다
     */
    private final static SingletonService instance = new SingletonService();
    private final static String test = "";

    // 조회시 사용
    public static SingletonService getInstance() {
        return instance;
    }
}
