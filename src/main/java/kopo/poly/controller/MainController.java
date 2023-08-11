package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    @GetMapping("/main/index")
    public String main() throws Exception {
        log.info(this.getClass().getName() + ".main 페이지 보여주는 함수 실행");
        return "/main/index";
    }
    @GetMapping("/main/about")
    public String about() throws Exception {
        log.info(this.getClass().getName() + ".about 페이지 보여주는 함수 실행");
        return "/main/about";
    }
    @GetMapping("/main/contact")
    public String contact() throws Exception {
        log.info(this.getClass().getName() + ".contact 페이지 보여주는 함수 실행");
        return "/main/contact";
    }
    @GetMapping("/main/shop")
    public String shop() throws Exception {
        log.info(this.getClass().getName() + ".shop 페이지 보여주는 함수 실행");
        return "/main/shop";
    }
    @GetMapping("/main/single")
    public String single() throws Exception {
        log.info(this.getClass().getName() + ".single 페이지 보여주는 함수 실행");
        return "/main/single";
    }
    @GetMapping("/main/purchaseList")
    public String purchaseList() throws Exception {
        log.info(this.getClass().getName() + ".single 페이지 보여주는 함수 실행");
        return "/main/purchaseList";
    }

}
