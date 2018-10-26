package com.ant.be.shiro.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        // 对响应头进行CORS授权
        MyCorsRegistration corsRegistration = new MyCorsRegistration("*");
        List<String> allowedOrigins = new ArrayList<>();
        allowedOrigins.add("http://127.0.0.1:4201");
        allowedOrigins.add("http://www.kaimadata.com.cn");
		allowedOrigins.add("http://114.116.28.17:4201");
		allowedOrigins.add("http://114.116.28.17");
		allowedOrigins.add("http://114.116.28.17:80");
        String[] objects = allowedOrigins.toArray(new String[allowedOrigins.size()]);

        corsRegistration
                //允许向该服务器提交请求的URI，*表示全部允许
                .allowedOrigins(objects)
                //允许提交请求的方法，*表示全部允许
                .allowedMethods("*")
                //允许的头信息,*标识全部允许
                .allowedHeaders("*")
                //暴露的头信息
//                .exposedHeaders(HttpHeaders.SET_COOKIE)
                //允许Cookie跨域，在做登录校验的时候有用
                .allowCredentials(true)
                //预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
                .maxAge(3600);

        // 注册CORS过滤器
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        //第一个参数表示过滤的url,*表示过滤所有
        configurationSource.registerCorsConfiguration("/**", corsRegistration.getCorsConfiguration());
        CorsFilter corsFilter = new CorsFilter(configurationSource);

        return new FilterRegistrationBean(corsFilter);
    }
    
    public class MyCorsRegistration extends CorsRegistration {
        public MyCorsRegistration(String pathPattern) {
            super(pathPattern);
        }

        @Override
        public CorsConfiguration getCorsConfiguration() {
            return super.getCorsConfiguration();
        }
    }

}
