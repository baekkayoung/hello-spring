package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "spring !!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String gelloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http header와 body 부 중 body에 이 데이터를 포함하겠다
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; // "hello XXXX" view가 아닌 문자 그대로 내려감
    }

    @GetMapping("hello-api")
    @ResponseBody // 객체가 반환되면 json 방식으로 데이터를 만들어서 http 응답에 반환
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
