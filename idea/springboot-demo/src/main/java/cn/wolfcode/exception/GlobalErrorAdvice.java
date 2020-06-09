package cn.wolfcode.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorAdvice {
    @ExceptionHandler(Exception.class)
    public String error(Model model,Exception e){
        model.addAttribute("error",e.getMessage());
        return "freemarker/err";
    }
}
