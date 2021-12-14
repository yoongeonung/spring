package jp.ac.hal.yoongeonung.spring_mvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Slf4j
@RestController
public class MappingController {

    /**
     * 기본 요청
     * 둘다 허용 /hello-basic, /hello-basic/
     * HTTP 메서드 모두 허용 GET, HEAD, POST, PUT, PATCH, DELETE
     **/
    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.debug("helloBasic = {}", getClass());
        log.debug("helloBasic");
        return "ok";
    }

    /**
     * method 특정 HTTP 메서드 요청만 허용
     * GET, HEAD, POST, PUT, PATCH, DELETE
     */
    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.debug("mapping get v1");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션 (코드보기) * @GetMapping
     *
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping("/mapping-get-v2")
    public String mappingGetV2() {
        log.debug("mapping get v2");
        return "ok";
    }

    /**
     * PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String id) {
        log.debug("userId = {}", id);
        return "ok";
    }

    /*
     * 변수명이 같으면 생략 가능
     */
    @GetMapping("/mapping/v1/{userId}")
    public String mappingPathV2(@PathVariable String userId) {
        log.debug("userId = {}", userId);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     *
     * @GetMapping("/mapping/users/{userId}/orders/{orderId}")
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.debug("userId = {}, orderId = {}", userId, orderId);
        log.debug("orderId type is = {}", orderId.getClass());
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.debug("mapping param");
        return "OK";
    }

    /**
     *특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes / json");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type * produces = "text/html"
     * produces = "!text/html" * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_PLAIN_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
