package hello.advanced.pointcut

import hello.advanced.member.MemberService
import io.kotest.core.spec.style.FunSpec
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AtAnnotationTest : FunSpec() {

    private val log = KotlinLogging.logger {}

    @Autowired
    private lateinit var memberService: MemberService

    init {
        this.test("success") {
            log.info("memberService Proxy=${memberService::class.java}")
            memberService.hello("helloA")
        }
    }

    @Aspect
    //@Component
    class AtAnnotationAspect {

        private val log = KotlinLogging.logger {}

        @Around("@annotation(hello.advanced.member.annotation.MethodAop)")
        fun doAtAnnotation(joinPoint: ProceedingJoinPoint): Any? {
            log.info("[@annotation] ${joinPoint.signature}")
            return joinPoint.proceed()
        }

    }
}
