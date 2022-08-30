package org.freeride.shootbug.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/8/30 17:49
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //解决ResponseBodyAdvice无法处理String的情况
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }
}
