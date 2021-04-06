package com.yue.validation;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class XssInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       
        //Write Code here 
        try{
            Enumeration<String> paramNames = request.getParameterNames();
            while(paramNames.hasMoreElements()){
                String key = (String)paramNames.nextElement();
                String val = request.getParameter(key);
                
                if(xssCheck(val)){
                    request.setAttribute("unsafe-request", "true");
                    break;
                }
            }
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        return true;
    }
    
    private boolean xssCheck(String value) {
        if (value != null) {
            return (value.matches("<script>(.*?)</script>") || value.matches("\"<script(.*?)>\""));
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
        
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
        
    }
}
