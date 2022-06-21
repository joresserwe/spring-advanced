package hello.advanced.pointcut

import hello.advanced.member.MemberServiceImpl
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import mu.KotlinLogging
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ArgsTest : FunSpec() {

    private val log = KotlinLogging.logger {}

    private val pointcut = AspectJExpressionPointcut()

    init {
        val method =
            MemberServiceImpl::class.java.getMethod("hello", String::class.java)


        this.context("args 잘안쓴다 / Parmeter와 매칭") {

            test("String 매칭") {
                pointcut.expression = "args(String)"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("Parameter의 부모타입도 허용한다. execution은 안됨") {
                pointcut.expression = "args(Object)"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("parameter가 없을 때") {
                pointcut.expression = "args()"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe false
            }

            test("parameter 타입/개수 상관 x") {
                pointcut.expression = "args(..)"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("parameter 타입 상관 x 1개") {
                pointcut.expression = "args(*)"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("parameter 처음엔 String, 이후엔 타입 개수 상관 x") {
                pointcut.expression = "args(String, ..)"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }
        }
    }
}
