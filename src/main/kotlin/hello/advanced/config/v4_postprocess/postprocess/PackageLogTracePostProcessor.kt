package hello.advanced.config.v4_postprocess.postprocess

import mu.KotlinLogging
import org.springframework.aop.Advisor
import org.springframework.aop.framework.ProxyFactory
import org.springframework.beans.factory.config.BeanPostProcessor

class PackageLogTracePostProcessor(
    private val basePackage: String,
    private val advisor: Advisor
) : BeanPostProcessor {

    private val log = KotlinLogging.logger {}

    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        log.info { "param beanName=$beanName bean=${bean::class.java}" }

        // Proxy 적용 대상 여부 체크
        // Proxy 적용 대상이 아니면 원본 진행
        // Base package가 아니면 원본을 그냥 넣음
        val packageName = bean::class.java.packageName
        if (!packageName.contains(basePackage)) return bean

        // proxy 생성 후 바꿈
        val proxyFactory = ProxyFactory(bean)
        proxyFactory.addAdvisor(advisor)
        proxyFactory.isProxyTargetClass = true
        val proxy = proxyFactory.proxy
        log.info("create proxy: target=${bean::class.java} proxy=${proxy::class.java}")
        return proxy
    }
}
