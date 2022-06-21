package hello.advanced.pointcut

import hello.advanced.member.MemberService
import hello.advanced.member.annotation.ClassAop
import hello.advanced.member.annotation.MethodAop
import io.kotest.core.spec.style.FunSpec
import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(ParameterTest.ParameterAspect::class)
class ParameterTest : FunSpec() {
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
    class ParameterAspect {
        private val log = KotlinLogging.logger {}

        @Pointcut("execution(* hello.advanced.member..*(..))")
        private fun allMember() {
        }

        @Around("allMember()")
        fun logArgs1(joinPoint: ProceedingJoinPoint): Any? {
            val arg1 = joinPoint.args[0]
            log.info { "[logArgs1] ${joinPoint.signature}, args=$arg1" }
            return joinPoint.proceed()
        }

        @Around("allMember() && args(arg, ..)")
        fun logArgs2(joinPoint: ProceedingJoinPoint, arg: Any): Any? {
            log.info { "[logArgs1] ${joinPoint.signature}, args=$arg" }
            return joinPoint.proceed()
        }

        @Before("allMember() && args(arg, ..)")
        fun logArgs3(arg: String) {
            log.info { "[logArgs3] arg=${arg}" }
        }

        @Before("allMember() && this(obj)")
        fun thisArgs(joinPoint: JoinPoint, obj: MemberService) {
            log.info { "[this]${joinPoint.signature}, obj=${obj::class.java}" }
        }

        @Before("allMember() && target(obj)")
        fun targetArgs(joinPoint: JoinPoint, obj: MemberService) {
            log.info { "[target]${joinPoint.signature}, obj=${obj::class.java}" }
        }

        @Before("allMember() && @target(annotation)")
        fun atTarget(joinPoint: JoinPoint, annotation: ClassAop) {
            log.info { "[@target]${joinPoint.signature}, obj=${annotation}" }
        }

        @Before("allMember() && @within(annotation)")
        fun atWithin(joinPoint: JoinPoint, annotation: ClassAop) {
            log.info { "[@within]${joinPoint.signature}, obj=${annotation}" }
        }

        @Before("allMember() && @annotation(annotation)")
        fun atAnnotation(joinPoint: JoinPoint, annotation: MethodAop) {
            log.info { "[@annotation]${joinPoint.signature}, annotationValue=${annotation.value}" }
        }
    }

}
