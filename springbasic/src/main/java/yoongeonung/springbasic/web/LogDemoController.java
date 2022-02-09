package yoongeonung.springbasic.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import yoongeonung.springbasic.common.MyLogger;
import yoongeonung.springbasic.logdemo.LogDemoService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> provider; // provider를 사용한 예외 해결
    private final LogDemoService logDemoService;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
//        MyLogger myLogger = provider.getObject();
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger.getClass() = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }

}
