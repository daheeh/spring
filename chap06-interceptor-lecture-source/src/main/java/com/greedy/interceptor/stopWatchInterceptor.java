package com.greedy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/* 
 * default 메소드 이전에는 모두 오버라이딩 헤야하는 책임을 가지기 때문에 HandlerInterceptorAdaptor를 이용해서
 * 부담을 줄여 사용했으나 default메소드가 인터페이스에 사용 가능하게된 1.8 이후부터는 인터페이스만 구현하여
 * 필요한 메소드만 오버라이딩해서 사용할 수 있다.
 *  */
public class stopWatchInterceptor implements HandlerInterceptor{ 
	
	private final MenuService menuService; // menuService : 빈이 담겨있음(@Component를 기준으로 Spring Container가 실행됐을때 생성됨)
	
	@Autowired
	public stopWatchInterceptor(MenuService menuService) {
		this.menuService = menuService;
	}

	/* 전처리 메소드 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandler 호출함...");
		
		long startTime = System.currentTimeMillis();
		
		request.setAttribute("startTime", startTime); 
		
		/* true면 컨트롤러를 이어서 호출, false이면 핸들러 메소드를 호출하지 않는다. */
		return true;
		
	}

	/* 후처리 메소드 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("postHandler 호출함...");
		
		long startTime = (Long)request.getAttribute("startTime");
		request.removeAttribute("startTime");
		
		long endTime = System.currentTimeMillis();
		
		modelAndView.addObject("interval", endTime - startTime); 
	}

	/* 마지막에 호출되는 메소드 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterComplete 호출함...");
		
		menuService.method();
	}
	
	
}
