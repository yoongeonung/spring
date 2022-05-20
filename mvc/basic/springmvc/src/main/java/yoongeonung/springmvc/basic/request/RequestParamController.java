package yoongeonung.springmvc.basic.request;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import yoongeonung.springmvc.basic.HelloData;

@Slf4j
@Controller
public class RequestParamController {

  /*
  반환형이 void인데 응답에 값을 직접 써넣으면 view를 조회하지 않는다.
   */
  @RequestMapping("/request-param-v1")
  public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    log.info("username = {}, age = {} ", username, age);

    response.getWriter().write("OK");
  }

  @ResponseBody
  @RequestMapping("/request-param-v2")
  public String requestParamV2(@RequestParam("username") String memberName,
      @RequestParam("age") int memberAge) {
    log.info("memberName = {} , memberAge = {} ", memberName, memberAge);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-v3")
  public String requestParamV3(
      @RequestParam String username,
      @RequestParam int age) {
    log.info("username = {}, age = {} ", username, age);
    return "ok-v3";
  }

  @ResponseBody
  @RequestMapping("/request-param-v4")
  public String requestParamV4(String username, int age) {
    log.info("username = {}, age = {} ", username, age);
    return "ok-v4";
  }

  @ResponseBody
  @RequestMapping("/request-param-required")
  public String requestParamRequired(@RequestParam(required = false) String username, @RequestParam(required = false) Integer age) {
    log.info("username = {} ", username);
    log.info("age = {} ", age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-default")
  public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
      @RequestParam(defaultValue = "-1") int age) {
    log.info("username = {} ", username);
    log.info("age = {} ", age);
    return "ok";
  }

  @ResponseBody
  @RequestMapping("/request-param-map")
  public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
    log.info("paramMap.get(\"username\") = {} ", paramMap.get("username"));
    log.info("paramMap.get(\"age\") = {} ", paramMap.get("age"));
    return "ok";
  }

  // 파라미터의 값이 1개가 확실하다면 Map 을 사용해도 되지만, 그렇지 않다면 MultiValueMap 을 사용하자.
  @ResponseBody
  @RequestMapping("/request-param-multimap")
  public String requestParamMultiMap(@RequestParam MultiValueMap<String, Object> multiValueMap) {
    List<Object> usernames = multiValueMap.get("username");
    for (Object username : usernames) {
      System.out.println("username = " + username);
    }

    return "ok";
  }

  /**
   * @ModelAttribute 사용 참고: model.addAttribute(helloData) 코드도 함께 자동 적용됨, 뒤에 model을 설명할 때 자세히 설명
   */
  @ResponseBody
  @RequestMapping("/model-attribute-v1")
  public String modelAttribute(@ModelAttribute HelloData helloData) {
    log.info("helloData = {} ", helloData);
    return "ok";
  }

  /**
   * @ModelAttribute 생략 가능
   * String, int 같은 단순 타입 = @RequestParam
   * 나머지는 @ModelAttribute(argument resolver 로 지정해둔 타입 외)
   */

  @ResponseBody
  @RequestMapping("/model-attribute-v2")
  public String modelAttributeV2(HelloData helloData, String username) {
    log.info("helloData = {} ", helloData);
    log.info("username = {} ", username);
    return "ok";
  }




}
