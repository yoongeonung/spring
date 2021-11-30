package jp.ac.hal.yoongeonung.springboot.singleton;

public class SingletonService {

    /**
     * 싱글톤 패턴
     * 자기 자신을 private final static 으로 선언
     * 자바애플리케이션이 뜨면서 자기자신을 생성해서 instance 참조변수에 넣어둔다
     */
    private final static SingletonService instance = new SingletonService();
    private final static String test = "";

    // public으로 열어서 객체 인스턴스가 필요할시 이 메서드를 통해서만 조회 사용
    public static SingletonService getInstance() {
        return instance;
    }

    // private 생성자를 생성해서 외부에서 생성하는것을 막는다
    private SingletonService() {
    }

    // 그냥 로직
    private void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
