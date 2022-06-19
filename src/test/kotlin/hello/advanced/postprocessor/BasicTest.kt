package hello.advanced.postprocessor

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import mu.KotlinLogging
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BasicTest : DescribeSpec({
    describe("Basic Config를 설정한다.") {
        val applicationContext = AnnotationConfigApplicationContext(BasicConfig::class.java)
        it("Bean A는 등록 돼 있어야 한다.") {
            val a = applicationContext.getBean("beanA", A::class.java)
            a shouldBe instanceOf<A>()
            shouldNotThrowAny { a.helloA() }
        }
        it("Bean B는 등록되지 않았다.") {
            shouldThrow<NoSuchBeanDefinitionException> { applicationContext.getBean("beanB", B::class.java) }
        }
    }
}) {

    @Configuration
    class BasicConfig {
        @Bean("beanA")
        fun a() = A()
    }

    class A {
        private val log = KotlinLogging.logger {}
        fun helloA() {
            log.info("helloA")
        }
    }

    class B {
        private val log = KotlinLogging.logger {}
        fun helloB() {
            log.info("helloB")
        }
    }
}
