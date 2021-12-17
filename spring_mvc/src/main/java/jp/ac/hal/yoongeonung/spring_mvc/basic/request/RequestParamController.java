package jp.ac.hal.yoongeonung.spring_mvc.basic.request;

import jp.ac.hal.yoongeonung.spring_mvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * 반환 타입이 없으면서(void) 이렇게 응답에 값을 직접 집어넣으면, view 조회X
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("OK");
    }

    /**
     * @RequestParam 사용
     * - 파라미터 이름으로 바인딩
     * @ResponseBody 추가
     * - View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String username,
                                 @RequestParam("age") int age) {
        log.info("username = {} , age = {}", username, age);
        return "OK";
    }

    /**
     * @RequestParam 사용
     * HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx")의
     * (name="xx") 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username = {} , age = {}", username, age);
        return "OK";
    }


    /**
     * @RequestParam 사용
     * String, int 등의 단순 타입(자작타입등이 아닌)이면 @RequestParam 도 생략 가능
     * 물론 요청파라미터의 key name과 변수명이 같아야한다.
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, Long age) {
        log.info("username = {} , age = {}", username, age);
        return "OK";
    }

    /**
     * @RequestParam.required /request-param -> username이 없으므로 예외
     * <p>
     * 주의!
     * /request-param?username= -> 빈문자로 통과
     * <p>
     * 주의!
     * /request-param
     * int age -> null을 int에 입력하는 것은 불가능, 따라서 Integer 변경해야 함
     * (또는 다음에 나오는 defaultValue 사용)
     * <p>
     * required=true(default)
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam String username,
            @RequestParam(required = false) Integer age
    ) {
        log.info("username = {} , age = {}", username, age);
        return "OK";
    }

    /**
     * @RequestParam - defaultValue 사용 *
     * 참고: defaultValue는 빈 문자의 경우에도 적용
     * /request-param?username=
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                      @RequestParam(required = false, defaultValue = "-1") Integer age) {
        log.info("username = {} , age = {}", username, age);
        return "OK";
    }


    /**
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...] ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, String> paramMap
    ) {
        String username = paramMap.get("username");
        String age = paramMap.get("age");
        log.info("username = {} , age = {}", username, age);
        return "OK";
    }

    /**
     * map으로 받을시 null체크가 까다롭다.
     * null값에 대해서는 일단 String으로 캐스트해서 받은뒤
     * 애플리케이션에서 직접 처리하는게 좋을듯하다.
     */
    @ResponseBody
    @RequestMapping("/request-param-map2")
    public String requestParamMap2(
            @RequestParam Map<String, Object> paramMap
    ) {
        String username = (String) paramMap.get("username");
//        Integer age = Integer.valueOf((String) paramMap.get("age"));
        String age = (String) paramMap.get("age");
        log.info("username = {} , age = {}", username, age);
        return "OK";
    }

    /**
     * MultiValueMap<T, T> paramMap;
     * /request-param-multivaluemap?username=kakao&username=line&age=11&age=22
     */
    @ResponseBody
    @RequestMapping("/request-param-multivaluemap")
    public String requestParamMap3(
            @RequestParam MultiValueMap<String, Object> paramMap
    ) {
        List<Object> names = paramMap.get("username");
        List<Object> ages = paramMap.get("age");
        for (Object name : names) {
            name = (String) name;
            log.info("username = {}", name);
        }
        for (Object age : ages) {
            age = Integer.parseInt((String) age);
            log.info("age = {}", age);
        }
        return "OK";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        /**
         * @ModelAttribute가 아래의 코드를 대신해준다.
         * 해당 객체에 프로퍼티 접근법으로 setter를 호출하여
         * request parameter에서 받아온 값을 바인딩해준다.
         * 참고: model.addAttribute(helloData) 코드도 함께 자동 적용됨
         *
         * HelloData helloData = new HelloData();
         * helloData.setUsername(username);
         * helloData.setAge(age);
         */
        log.info("helloData = {}}", helloData);
        return "ok";
    }

    /**
     * @ModelAttribute 생략 가능
     * String, int 같은 단순 타입 = @RequestParam
     * argument resolver 로 지정해둔 타입 외 = @ModelAttribute
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("helloData = {}}", helloData);
        return "ok";
    }
}
