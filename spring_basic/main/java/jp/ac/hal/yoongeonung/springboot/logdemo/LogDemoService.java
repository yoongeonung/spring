package jp.ac.hal.yoongeonung.springboot.logdemo;

import jp.ac.hal.yoongeonung.springboot.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    //        private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger; // proxy를 주입

    public void logic(String id) {
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("Service id = " + id);
    }

}
