package jp.ac.hal.yoongeonung.springboot.web.frontcontroller.v3;

import jp.ac.hal.yoongeonung.springboot.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
