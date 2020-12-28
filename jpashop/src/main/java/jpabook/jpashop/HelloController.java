package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!");
        return "hello";


        /*
            model : data를 실어서 넘길 수 있다
            return : 화면이름 resources - templates 아래 이름과 자동으로 맵핑됨
            서버사이드 렌더링?
         */
    }
}
