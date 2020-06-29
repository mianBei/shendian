package com.example.managercontroller.config;

import com.example.common.superCon.SuperController;
import com.example.common.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Configuration
public class ConfigurationFilter {
    @Bean
    public FilterRegistrationBean filterRegistration(){
        //zenmhuishi
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AppFilter());
        //拦截规则
        registration.addUrlPatterns("*.htm");
        //过滤器名称
        registration.setName("filter");
        //过滤器顺序
        registration.setOrder(1);
        return registration;
    }

    public class AppFilter extends SuperController implements Filter {



        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }


        private boolean isNoCheckUrl(String url){
            String[] noCheckUrlArr={
                    "test.htm"
            };
            boolean b=false;
            for(String str:noCheckUrlArr){
                if(url.indexOf(str)!=-1){
                    b=true;
                    break;
                }
            }
            return b;
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                            throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response=(HttpServletResponse)servletResponse;
            //打印请求Url
            String uri=request.getRequestURI();
            System.out.println("this is filter,url :" + uri);
            //HashMap<String,Object> paramMap= Util.genHmParam(request);
            boolean isNoCheckUrl=isNoCheckUrl(uri);
            if(isNoCheckUrl){
                filterChain.doFilter(servletRequest, servletResponse);
            }else{
                filterChain.doFilter(servletRequest, servletResponse);
                //定义签名规则
            }
        }

        @Override
        public void destroy() {

        }
    }
}
