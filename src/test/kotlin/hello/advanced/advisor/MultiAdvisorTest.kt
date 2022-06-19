package hello.advanced.advisor

import hello.advanced.common.service.ServiceInterface
import hello.advanced.common.service.ServiceInterfaceImpl
import io.kotest.core.spec.style.FunSpec
import mu.KotlinLogging
import org.aopalliance.intercept.MethodInterceptor
import org.aopalliance.intercept.MethodInvocation
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor

class MultiAdvisorTest : FunSpec() {

    private val log = KotlinLogging.logger {}

    init {
        test("여러 프록시 사용") {
            /* client -> proxy2(advisor2) -> proxy1(advisor1) -> target */

            val target = ServiceInterfaceImpl()

            // proxy 1
            val proxyFactory1 = ProxyFactory(target) // proxy1 -> target
            val advisor1 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
            proxyFactory1.addAdvisor(advisor1)
            val proxy1 = proxyFactory1.proxy as ServiceInterface

            // proxy 2
            val proxyFactory2 = ProxyFactory(proxy1) // proxy2 -> proxy1
            val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())
            proxyFactory2.addAdvisor(advisor2)
            val proxy2 = proxyFactory2.proxy as ServiceInterface

            proxy2.save()
            proxy2.find()
        }

        test("하나의 proxy 여러 advisor") {
            /* client -> proxy2(advisor2) -> proxy1(advisor1) -> target */

            // proxyFactory
            val target = ServiceInterfaceImpl()
            val proxyFactory = ProxyFactory(target) // proxy1 -> target

            // advisor1, advisor2
            val advisor1 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice1())
            val advisor2 = DefaultPointcutAdvisor(Pointcut.TRUE, Advice2())
            proxyFactory.addAdvisors(advisor2, advisor1) // 순서대로 넣어야함 client -> advisor2 -> advisor1 -> target
            val proxy = proxyFactory.proxy as ServiceInterface

            proxy.save()
            proxy.find()
        }
    }

    inner class Advice1 : MethodInterceptor {
        override fun invoke(invocation: MethodInvocation): Any? {
            log.info { "advice1 호출" }
            return invocation.proceed()
        }
    }

    inner class Advice2 : MethodInterceptor {
        override fun invoke(invocation: MethodInvocation): Any? {
            log.info("advice2 호출")
            return invocation.proceed()
        }
    }
}
