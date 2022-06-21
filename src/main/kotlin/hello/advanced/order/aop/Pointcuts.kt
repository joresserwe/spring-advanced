package hello.advanced.order.aop

import org.aspectj.lang.annotation.Pointcut

class Pointcuts {
    // hello.advanced.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.advanced.order..*(..))")
    fun allOrder() {
    }

    // 클래스 이름 패턴이 *Service
    @Pointcut("execution(* *..*Service.*(..))")
    fun allService() {
    }

    // allOrder && allService
    @Pointcut("allOrder() && allService()")
    fun orderAndService() {
    }

}
