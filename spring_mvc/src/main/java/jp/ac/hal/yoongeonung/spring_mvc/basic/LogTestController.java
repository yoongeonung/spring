package jp.ac.hal.yoongeonung.spring_mvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    // slf4j - logback
    // Lombok으로 대체 가능
    // private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";
        System.out.println("name = " + name);

        log.trace("trace = {}", name);
        log.debug("debug = {}", name);
        log.info("info = {}", name);
        log.warn("warn = {}", name);
        log.error("error = {}", name);

        log.info("info log={} this is {}", name, "test");
        return "ok";
    }
}
