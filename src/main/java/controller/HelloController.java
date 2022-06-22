package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


//localhost:8080  // index.jsp가 기본 설정이다. 따라서 index.jsp가 출력된다.
// url/helloBody를 입력하면 hello world가 뜬다.
//매핑을 통해 주소와 반환할 것을 결정한다.
//dispatcher-servlet.xml을 통해 prefix , suffix 를 통해
//빈으로 등록할 어노테이션을 찾아 컨테이너에 넣는다.
@Controller
public class HelloController {
    //매핑을 한다.
    @RequestMapping(value ="/helloBody", method = RequestMethod.GET)
    @ResponseBody
    public String hello()
    {
        //접근을 출력하여 알린다.
        System.out.println("/helloBody");
        //for문을 이용하여 메소드 실행시간을 조금이나마 늘린다.
        for(int i=0;i<100000;++i);
        return "hello world!";
    }

    //매핑을 한다
    @RequestMapping(value ="hello", method = RequestMethod.GET)
    public String hello2() {
        //접근을 출력하여 알린다.
        System.out.println("/hello");
        //for문을 이용하여 메소드 실행시간을 조금이나마 늘린다.
        for(int i=0;i<100000;++i);
        return "hello";
    }
}
