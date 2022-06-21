package hello.advanced.pointcut

import hello.advanced.member.annotation.ClassAop
import io.kotest.core.spec.style.FunSpec
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component

@SpringBootTest
class AtTargetAtWithinTest : FunSpec() {

    private val log = KotlinLogging.logger {}

    @Autowired
    private lateinit var child: Child

    init {
        this.test("success") {
            log.info { "child Proxy=${child::class.java}" }
            child.childMethod()
            child.parentMethod()
        }
    }

    @Component
    class Parent {
        fun parentMethod() {}
    }

    @ClassAop
    @Component
    class Child : Parent() {
        fun childMethod() {}
    }

    @Aspect
    //@Component
    class AtTargetAtWithinAspect {

        private val log = KotlinLogging.logger {}

        // @target: 부모 타입의 Method도 적용됨
        @Around("execution(* hello.advanced..*(..)) && @target(hello.advanced.member.annotation.ClassAop)")
        fun atTarget(joinPoint: ProceedingJoinPoint): Any? {
            log.info { "[@target] ${joinPoint.signature}" }
            return joinPoint.proceed()
        }

        // @within:부모 타입의 Method는 적용되지 않음
        @Around("execution(* hello.advanced..*(..)) && @within(hello.advanced.member.annotation.ClassAop)")
        fun atWithin(joinPoint: ProceedingJoinPoint): Any? {
            log.info { "[@within] ${joinPoint.signature}" }
            return joinPoint.proceed()
        }
    }


}
