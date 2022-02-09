package yoongeonung.springbasic.logdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoongeonung.springbasic.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger; // 기존 코드 그대로 프록시를 통한 예외 해결
//    private final ObjectProvider<MyLogger> provider; // provider를 사용한 예외 해결

    public void logic(String testId) {
//        MyLogger myLogger = provider.getObject();
        myLogger.log("service id = " + testId);
        System.out.println("myLogger.getClass() = " + myLogger.getClass());
    }
}
