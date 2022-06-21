package hello.advanced.pointcut

import hello.advanced.member.MemberServiceImpl
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExecutionTest : FunSpec() {

    private val pointcut = AspectJExpressionPointcut()

    private val method =
        MemberServiceImpl::class.java.getMethod("hello", String::class.java)

    private fun getMatchedResult(expression: String): Boolean {
        pointcut.expression = expression
        return pointcut.matches(method, MemberServiceImpl::class.java)
    }

    init {

        this.context("Method 명에 대한 execution") {

            test("가장 긴 형태") {
                val expression = "execution(public String hello.advanced.member.MemberServiceImpl.hello(String))"
                getMatchedResult(expression) shouldBe true
            }

            test("가장 짧은 형태") {
                val expression = "execution(* *(..))"
                getMatchedResult(expression) shouldBe true
            }

            test("모든 선언 타입 중 hello()") {
                val expression = "execution(* hello(..))"
                getMatchedResult(expression) shouldBe true
            }

            test("wildcard 사용") {
                val expression = "execution(* *el*(..))"
                getMatchedResult(expression) shouldBe true
            }
        }

        this.context("Package 관련") {

            test("Package, Class, Method 정확 / Parameter 상관x") {
                val expression = "execution(* hello.advanced.member.MemberServiceImpl.hello(..))"
                getMatchedResult(expression) shouldBe true
            }

            test("Package 정확 / Class, Method 상관x") {
                val expression = "execution(* hello.advanced.member.*.*(..))"
                getMatchedResult(expression) shouldBe true
            }

            test("hello.advanced 하위 전체(..사용)") {
                val expression = "execution(* hello.advanced..*.*(..))"
                pointcut.expression = "execution(* hello.advanced..*.*(..))"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("Class까지 정확하게 일치") {
                pointcut.expression = "execution(* hello.advanced.member.MemberServiceImpl.*(..))"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("Method가 부모 타입에 있다면 넣어도 괜찮다.") {
                pointcut.expression = "execution(* hello.advanced.member.MemberService.*(..))"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("Method가 자식에만 있으면 False.") {
                pointcut.expression = "execution(* hello.advanced.member.MemberService.*(..))"
                val method1 = MemberServiceImpl::class.java.getMethod("internal", String::class.java)
                pointcut.matches(method1, MemberServiceImpl::class.java) shouldBe false
            }
        }

        this.context("Parameter 관련") {

            test("String Type의 Parmeter 허용") {
                pointcut.expression = "execution(* *(String))"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("Parameter가 없어야 함") {
                pointcut.expression = "execution(* *())"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe false
            }

            test("정확히 하나의 Parameter만 허용, 모든 Type 가능") {
                pointcut.expression = "execution(* *(*))"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("숫자와 무관하게 모든 Parameter, 모든 Type 허용") {
                pointcut.expression = "execution(* *(..))"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }

            test("숫자와 무관하게 모든 Parameter, String Type으로 시작해야함") {
                pointcut.expression = "execution(* *(String, ..))"
                pointcut.matches(method, MemberServiceImpl::class.java) shouldBe true
            }
        }
    }
}
