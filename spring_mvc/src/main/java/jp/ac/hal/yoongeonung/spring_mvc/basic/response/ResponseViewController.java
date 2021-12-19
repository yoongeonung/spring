package jp.ac.hal.yoongeonung.spring_mvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("/response/hello");
        mav.addObject("data", "kakao");
        return mav;
    }

    /**
     *
     * @Controller가 있고 반환타입이 String인경우 -> 뷰 템플릿을 찾음
     * @Controller가 + @ResponseBody가 있고 반환타입이 String인경우 -> String을 그대로 바디에 실어서 보냄.
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "line");
        return "response/hello";
    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "clear");
        // @RequestMapping("/response/hello")와
        // view template의 경로가 같고 반환값이 void일경우
        // 스프링이 요청url과 같은 템플레잇을 찾아서 렌더링해준다.(비추천)
    }
}
