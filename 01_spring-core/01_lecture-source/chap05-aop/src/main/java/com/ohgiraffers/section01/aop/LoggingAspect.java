package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class LoggingAspect {

    //@Pointcut : 여러 조인포인트를 매치하기 위해 지정한 표현식
    // excution([수식어] 리턴타입 [클래스이름].이름(파라미터))
    // 수식어 생략 가능(public, private, protected, default)
    // *은 어떤 타입이든 상관이 없다 *Service는 어떤클래스든 Service이름이 들어가는 것이면 인식 *는 모든 메서드(..)는 매개변수의 갯수에 상관이 없다
    @Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
    public void logPointcut() {
    }

    //()안 동작할 포인터컷의 메소드를 작성합니다.
    @Before("logPointcut()")
    //JoinPoint는 반드시 들어가야합니다.
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before joinPoint.getTarget() : " + joinPoint.getTarget());
        System.out.println("Before joinPoint.getSignature() : " + joinPoint.getSignature());
        if (joinPoint.getArgs().length > 0) {
            System.out.println("Before joinPoint.getArgs()[0] : " + joinPoint.getArgs()[0]);
        }
    }

    //어노테이션에서 속성들에 값을 넣지 않으면 default속성값이 들어간다.
    @After("logPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After joinPoint.getTarget() : " + joinPoint.getTarget());
        System.out.println("After joinPoint.getSignature() : " + joinPoint.getSignature());
        if (joinPoint.getArgs().length > 0) {
            System.out.println("After joinPoint.getArgs()[0] : " + joinPoint.getArgs()[0]);
        }
    }

    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result){
        System.out.println("AfterReturning result : " + result);

        if(result != null && result instanceof Map){
            ((Map<Long, MemberDTO>) result).put(100L, new MemberDTO(100L,"반환값 가공"));
        }
    }

    @AfterThrowing(pointcut = "logPointcut()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        System.out.println("AfterThrowing exception : " + exception);
    }

    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        // 사전
        System.out.println("Around Before : " + joinPoint.getSignature().getName());

        // 원본 조인 포인트를 실행한다.
        Object result = joinPoint.proceed();

        // 사후
        System.out.println("Around After : " + joinPoint.getSignature().getName());

        return result;
    }
}
