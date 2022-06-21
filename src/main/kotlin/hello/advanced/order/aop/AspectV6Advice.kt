package hello.advanced.order.aop

import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*

@Aspect
class AspectV6Advice {

    private val log = KotlinLogging.logger {}

/*
    @Around("hello.advanced.order.aop.Pointcuts.orderAndService()")
    fun doTransaction(joinPoint: ProceedingJoinPoint): Any? {
        try {
            //@Before
            log.info { "[트랜잭션 시작] ${joinPoint.signature}" }
            val result = joinPoint.proceed()
            //@AfterReturning
            log.info { "[트랜잭션 커밋] ${joinPoint.signature}" }
            return result
        } catch (e: Exception) {
            //@AfterThrowing
            log.info { "[트랜잭션 롤백] ${joinPoint.signature}" }
            throw e
        } finally {
            //@After
            log.info { "[리소스 릴리즈] ${joinPoint.signature}" }
        }
    }
*/

    @Before("hello.advanced.order.aop.Pointcuts.orderAndService()")
    fun doTransaction(joinPoint: JoinPoint) {
        log.info { "[before] ${joinPoint.signature}" }
    }

    @AfterReturning("hello.advanced.order.aop.Pointcuts.orderAndService()", returning = "result")
    fun doReturn(joinPoint: JoinPoint, result: Any?) {
        log.info { "[return] ${joinPoint.signature} return=$result" }
    }

    @AfterThrowing("hello.advanced.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    fun doReturn(joinPoint: JoinPoint, ex: Exception) {
        log.info { "[ex] ${joinPoint.signature} ex=${ex.message}" }
    }

    @After("hello.advanced.order.aop.Pointcuts.orderAndService()")
    fun doAfter(joinPoint: JoinPoint) {
        log.info { "[result] ${joinPoint.signature}" }
    }
}
