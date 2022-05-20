package yoongeonung.springmvc.basic.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class RequestBodyStringController {

  /*
  반환형이 void 인데 응답에 값을 직접 써넣으면 view를 조회하지 않는다.
   */
  @PostMapping("/request-body-string-v1")
  public void requestBodyString(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    ServletInputStream inputStream = request.getInputStream();
    String result = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    log.info("result = {} ", result);

    response.getWriter().write("ok");
  }

  /**
   * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
   * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
   */
  @PostMapping("/request-body-string-v2")
  public void requestBodyStringV2(InputStream inputStream, Writer writer) throws IOException {
    String result = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
    log.info("result = {} ", result);

    writer.write("ok");
  }

  /**
   * HttpEntity: HTTP header, body 정보를 편라하게 조회
   * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttributeX)
   * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
   *
   * 응답에서도 HttpEntity 사용 가능 - 메시지 바디
   * 정보 직접 반환(view 조회X)
   * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
   */
  @PostMapping("/request-body-string-v3")
  public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
    log.info("httpEntity.getBody() = {} ", httpEntity.getBody());
    return new HttpEntity<>("ok");
  }


/**
 * @RequestBody
 * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
 * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
 * *
 *  @ResponseBody
 * - 메시지 바디 정보 직접 반환(view 조회X)
 * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
 * */
  @ResponseBody
  @PostMapping("/request-body-string-v4")
  public String requestBodyStringV4(@RequestBody String messageBody) {
    log.info("messageBody = {} ", messageBody);
    return "ok";
  }

  /**
   * RequestEntity HttpMethod, url 정보가 추가, 요청에서 사용 ResponseEntity HTTP 상태 코드 설정 가능, 응답에서 사용
   */

  @PostMapping("/request-body-string-v5")
  public ResponseEntity<String> requestBodyStringV5(RequestEntity<String> requestEntity) {
    log.info("requestEntity = {} ", requestEntity);
    return new ResponseEntity<>("ok", HttpStatus.CREATED);
  }


}
