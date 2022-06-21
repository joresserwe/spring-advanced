package hello.advanced.pointcut

import hello.advanced.member.MemberService
import io.kotest.core.spec.style.FunSpec
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

/**
 * application.properties
 * spring.aop.proxy-target-class=true   CGLIB 기본 적용 (기본값)
 * spring.aop.proxy-target-class=flase  JDK 동적 프록시(Interface) / CGLIB(구체)
 */
@Import(ThisTargetTest.ThisTargetAspect::class)
//@SpringBootTest(properties = ["spring.aop.proxy-target-class=false"]) // JDK 동적 프록시
@SpringBootTest(properties = ["spring.aop.proxy-target-class=true"]) // CGLIB(기본값)
class ThisTargetTest : FunSpec() {

    private val log = KotlinLogging.logger {}

    @Autowired
    private lateinit var memberService: MemberService

    init {
        this.test("success") {
            log.info { "memberService Proxy=${memberService::class.java}" }
            memberService.hello("helloA")
        }
    }

    @Aspect
    class ThisTargetAspect {
        private val log = KotlinLogging.logger {}

        @Around("this(hello.advanced.member.MemberService)")
        fun doThisInterface(joinPoint: ProceedingJoinPoint): Any? {
            log.info("[this-interface] ${joinPoint.signature}")
            return joinPoint.proceed()
        }

        @Around("target(hello.advanced.member.MemberService)")
        fun doTargetInterface(joinPoint: ProceedingJoinPoint): Any? {
            log.info("[target-interface] ${joinPoint.signature}")
            return joinPoint.proceed()
        }

        @Around("this(hello.advanced.member.MemberServiceImpl)")
        fun doThis(joinPoint: ProceedingJoinPoint): Any? {
            log.info("[this-impl] ${joinPoint.signature}")
            return joinPoint.proceed()
        }

        @Around("target(hello.advanced.member.MemberServiceImpl)")
        fun doTarget(joinPoint: ProceedingJoinPoint): Any? {
            log.info("[target-impl] ${joinPoint.signature}")
            return joinPoint.proceed()
        }
    }
}
