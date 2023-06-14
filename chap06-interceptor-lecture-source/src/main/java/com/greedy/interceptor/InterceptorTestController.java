package com.greedy.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class InterceptorTestController {

	@GetMapping("stopwatch") //main에서 여기로 들어올 때 stopWatchInterceptor가 중간에 빈을 가로챔(전처리됨) -> 여기서는 시간만 확인
	public String handlerMethod() throws InterruptedException {
		
		System.out.println("핸들러 메소드 호출함...");
		
		Thread.sleep(1000); // 1초 잠시 멈췄다가 다음 코드 실행(return)
		
		return "result";
	}
	
}
