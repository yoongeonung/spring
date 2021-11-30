package jp.ac.hal.yoongeonung.springboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 이전 예제들의 섫정정보들 때문에 제외
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {
}
