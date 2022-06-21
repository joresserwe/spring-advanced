package hello.advanced.pointcut

import hello.advanced.member.MemberServiceImpl
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import mu.KotlinLogging
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WithinTest : FunSpec() {

    private val log = KotlinLogging.logger {}

    private val pointcut = AspectJExpressionPointcut()

    init {
        val method =
            MemberServiceImpl::class.java.getMethod("hello", String::class.java)


        this.context("within은 잘 사용 안한다.") {

            test("타입 안에 있는 Method는 모두 가능") {
                pointcut.expression = "within(hello.advanced.member.MemberServiceImpl)"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("Wild Card 이용") {
                pointcut.expression = "within(hello.advanced.member.*Service*)"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("..으로 SubPackage 생략") {
                pointcut.expression = "within(hello.advanced..*)"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("타겟의 타입에만 직접 적용, Interface를 선정하면 안된다") {
                pointcut.expression = "within(hello.advanced.member.MemberService)"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe false
            }

            test("Execution은 Type기반, Interface를 선정 가능") {
                pointcut.expression = "execution(* hello.advanced.member.MemberService.*(..))"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }
        }
    }
}
