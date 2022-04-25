package yoongeonung.basic.logdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yoongeonung.basic.common.MyLogger;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;

    public void logic(String testId) {
        // ... other logics ...
        // print log at service
        myLogger.log("service id = " + testId);
    }
}
