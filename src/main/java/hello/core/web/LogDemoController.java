package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger; // 가짜 MyLogger을 집어넣음
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass()); // ㅇ때도 가짜 프록시 객체를 조회
        myLogger.setRequestURL(requestURL); // 실제 호출하는 시점에 진짜  MyLogger 를 찾아서 동작한다.
        // ㄱㄴㄲ 실제로 필요한 시점까지는 가짜로 버티다가 진짜 필요하면 그때 진짜를 찾으러 감
        // 스프링 컨테이너가 가진 강점 ( 코드를 수정하지 않아도 됨 ) , 유지보수 할 때 힘들어 질 수 있으니 주의해야 한다.
        // 꼭 필요한 곳에서만 최소화로 사용할 것 !

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}

