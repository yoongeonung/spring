package yoongeonung.springbasic.lifecycle;

import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {
    @Setter
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 url = " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    public void connect() {
        System.out.println("서비스 시작시 호출 : " + url);
    }

    public void disconnect() {
        System.out.println("close : " + url);
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

    @PostConstruct
    public void init() {
        connect();
        call("초기화 연결 메시지");
    }
}
