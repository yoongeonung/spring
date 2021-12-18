package jp.ac.hal.yoongeonung.spring_mvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    /**
     * 요청 파라미터와 다르게, HTTP 메시지 바디를 통해 데이터가 직접
     * 데이터가 넘어오는 경우는 @RequestParam , @ModelAttribute 를 사용할 수 없다.
     * (물론 HTML Form 형식으로 전달되는 경우는 요청 파라미터로 인정된다.)
     * HTTP 메시지 바디의 데이터를 InputStream 을 사용해서 직접 읽을 수 있다.
     * 항상 stream은 바이트코드 -> 받을때는 인코딩이 필요!
     */
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);
        response.getWriter().write("OK");
    }

    /**
     * HttpServletRequest와 HttpServletResponse를 불필요하게 전부 받을 필요는 없다.
     * Spring이 InputStream과 OutputStream, Writer만 따로 넘겨주기도 한다.
     * <p>
     * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
     */
    @PostMapping("request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody = {}", messageBody);
        responseWriter.write("OK");
    }

    /**
     * HTTP Converter를 이용
     * HttpEntity<T> http 스펙을 문서화해논것
     * HttpEntity: HTTP header, body 정보를 편라하게 조회
     * - 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용 *
     * 응답에서도 HttpEntity 사용 가능
     * - 메시지 바디 정보 직접 반환(view 조회X)
     * - HttpMessageConverter 사용 -> StringHttpMessageConverter 적용
     */
    @PostMapping("request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}", messageBody);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("CustomHeader", "Custom!!!");
        return new HttpEntity<String>("OK", headers);
    }

    @PostMapping("request-body-string-v3v2")
    public HttpEntity<String> requestBodyStringV3V2(RequestEntity<String> httpEntity) {
        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}", messageBody);
        // 상태코드 전달가능
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED) // HttpStatus도 Annotation으로 대체
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {
        /**
         * @RequestParam : Http 요청 메시지의 query parameter 조회
         * @RequestBody : Http 요청 메시지의 body 조회
         * @RequestHeader : Http 요청 메시지의 Header 조회
         */
        log.info("messageBody = {}", messageBody);
        return "OK";
    }
}
