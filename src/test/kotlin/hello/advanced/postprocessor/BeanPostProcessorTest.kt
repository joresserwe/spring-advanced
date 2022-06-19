package hello.advanced.postprocessor

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import mu.KotlinLogging
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanPostProcessorTest : DescribeSpec({
    describe("BeanPostProcessorConfig를 설정한다.") {
        val applicationContext = AnnotationConfigApplicationContext(BeanPostProcessorConfig::class.java)
        it("beanA는 beanB로 바꿔진다.") {
            val a = applicationContext.getBean("beanA", B::class.java)
            a shouldBe instanceOf<B>()
            shouldNotThrowAny { a.helloB() }
        }
        it("A는 Bean으로 등록되지 않는다.") {
            shouldThrow<NoSuchBeanDefinitionException> { applicationContext.getBean(A::class.java) }
        }
    }
}) {

    
    @Configuration
    class BeanPostProcessorConfig {
        @Bean("beanA")
        fun a() = A()

        @Bean
        fun helloPostProcessor() = AToBPostProcessor()
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

    class AToBPostProcessor : BeanPostProcessor {

        private val log = KotlinLogging.logger {}

        override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
            log.info { "beanName=$beanName bean=$bean" }
            return if (bean is A) B() else bean
        }
    }
}
