package jp.ac.hal.yoongeonung.springboot.web;

import jp.ac.hal.yoongeonung.springboot.common.MyLogger;
import jp.ac.hal.yoongeonung.springboot.logdemo.LogDemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    /**
     * 스프링컨테이너가 뜨면서 생성자를 이용하여 빈을 등록할때가 아니라
     * 내가 필요한 시점에 필요한 빈을 주입해서 사용할수 있게 해주는 ObjectProvider를 이용
     * 이용 안할시 에러발생 -> scope가 reuqest인데 스프링 생성시 주입하려 하기때문에
     */
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    /**
     * ObjectProvider도 좋지만 proxy 기술을 이용해서 보다 간단히 할 수 있다.
     */
    private final MyLogger myLogger;
    private final LogDemoService logDemoService;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject();
        System.out.println("myLogger = " + myLogger.getClass()); // CGLIB PROXY
        myLogger.setRequestURL(requestURL);
        myLogger.log("Controller Test");
        logDemoService.logic("testId");
        return "OK";
    }
}
