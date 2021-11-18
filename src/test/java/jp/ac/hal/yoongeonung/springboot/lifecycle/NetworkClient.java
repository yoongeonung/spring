package jp.ac.hal.yoongeonung.springboot.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient {
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("message = " + message + " call = " + url);
    }

    public void disconnect() {
        System.out.println("close : " + url);
    }

    public void destroy() {
        disconnect();
    }

    public void init() {
        connect();
        call("초기화 연결 메세지");
    }
}
