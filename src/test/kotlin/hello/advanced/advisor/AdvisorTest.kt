package hello.advanced.advisor

import hello.advanced.common.advice.TimeAdvice
import hello.advanced.common.service.ServiceInterface
import hello.advanced.common.service.ServiceInterfaceImpl
import io.kotest.core.spec.style.FunSpec
import mu.KotlinLogging
import org.springframework.aop.ClassFilter
import org.springframework.aop.MethodMatcher
import org.springframework.aop.Pointcut
import org.springframework.aop.framework.ProxyFactory
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.NameMatchMethodPointcut
import java.lang.reflect.Method

internal class AdvisorTest : FunSpec({
    test("Advisor Test") {
        val target = ServiceInterfaceImpl()
        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(Pointcut.TRUE, TimeAdvice())
        proxyFactory.addAdvisor(advisor)
        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save()
        proxy.find()
    }

    test("직접 PointCut 만듦") {
        val target = ServiceInterfaceImpl()
        val proxyFactory = ProxyFactory(target)
        val advisor = DefaultPointcutAdvisor(MyPointcut(), TimeAdvice())
        proxyFactory.addAdvisor(advisor)
        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save() // proxy 실행
        proxy.find() // proxy 실행안됨.
    }

    test("Spring이 제공하는 PointCut") {
        val target = ServiceInterfaceImpl()
        val proxyFactory = ProxyFactory(target)
        val pointcut = NameMatchMethodPointcut()
        pointcut.addMethodName("save")
        val advisor = DefaultPointcutAdvisor(pointcut, TimeAdvice())
        proxyFactory.addAdvisor(advisor)
        val proxy = proxyFactory.proxy as ServiceInterface

        proxy.save() // proxy 실행
        proxy.find() // proxy 실행안됨.
    }
}) {
    class MyPointcut : Pointcut {
        override fun getClassFilter(): ClassFilter {
            return ClassFilter.TRUE
        }

        override fun getMethodMatcher(): MethodMatcher {
            return MyMethodMatcher()
        }

    }

    class MyMethodMatcher : MethodMatcher {

        private val log = KotlinLogging.logger {}
        private val matchName = "save"

        override fun matches(method: Method, targetClass: Class<*>): Boolean {
            val result = method.name == matchName
            log.info("포인트컷 호출 method=${method.name} targetClass=$targetClass")
            log.info("포인트컷 결과 result=$result")
            return result
        }

        override fun matches(method: Method, targetClass: Class<*>, vararg args: Any?): Boolean {
            return false
        }

        override fun isRuntime(): Boolean {
            return false
        }

    }
}
