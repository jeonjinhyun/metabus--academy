package com.ohgiraffers.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/* 핸들러 인터셉터를 구현하면 된다.
 * default 메소드 이전에는 모두 오버라이딩 해야 하는 책임을 가지기 때문에 HandlerInterceptorAdaptor를 이용해 부담을 줄여 사용했으나
 * default 메소드가 인터페이스에서 사용 가능하게 된 1.8 이후부터는 인터페이스만 구현하여 필요한 메소드만 오버라이딩 해서 사용할 수 있다.
 * */
@Component
public class StopWatchInterceptor implements HandlerInterceptor {
	
	private final MenuService menuService;

	public StopWatchInterceptor(MenuService menuService) {
		this.menuService = menuService;
	}
	
	/* 전처리 메소드 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("preHandler 호출함...");
		
		long startTime = System.currentTimeMillis();
		
		request.setAttribute("startTime", startTime);
		
		/* true이면 컨트롤러를 이어서 호출한다. false이면 핸들러 메소드를 호출하지 않음 */
		return true;
	}
	
	/* 후처리 메소드 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		System.out.println("postHandler 호출함...");
		
		long startTime = (Long) request.getAttribute("startTime");
		request.removeAttribute("startTime");
		
		long endTime = System.currentTimeMillis();
		
		modelAndView.addObject("interval", endTime - startTime);
	}
	
	/* 마지막에 호출하는 메소드 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		
		System.out.println("afterComplate 호출함...");
		
		menuService.method();
	}
	
	/* 인터셉터 작성 후 WebMvcConfigurer 구현체에 addInterceptors() 메소드를 통해 등록해야 한다.
	 * 동작 확인 후 @Autowired로 bean 주입받아서 사용해보자(스프링 컨테이너 기능)
	 * */
}
