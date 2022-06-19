package hello.advanced

import hello.advanced.config.v4_postprocess.BeanPostProcessorConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

//@Import(AppV1Config::class, AppV2Config::class)
//@Import(InterfaceProxyConfig::class)
//@Import(ConcreteProxyConfig::class)
//@Import(DynamicProxyBasicConfig::class)
//@Import(DynamicProxyFilterConfig::class)
//@Import(ProxyFactoryConfigV1::class)
//@Import(ProxyFactoryConfigV2::class)
@Import(BeanPostProcessorConfig::class)
@SpringBootApplication(scanBasePackages = ["hello.advanced.app"])
class AdvancedApplication

fun main(args: Array<String>) {
    runApplication<AdvancedApplication>(*args)
}
