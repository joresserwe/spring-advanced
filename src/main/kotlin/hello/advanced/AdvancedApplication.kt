package hello.advanced

import hello.advanced.config.v2_dynamicproxy.DynamicProxyFilterConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

//@Import(AppV1Config::class, AppV2Config::class)
//@Import(InterfaceProxyConfig::class)
//@Import(ConcreteProxyConfig::class)
//@Import(DynamicProxyBasicConfig::class)
@Import(DynamicProxyFilterConfig::class)
@SpringBootApplication(scanBasePackages = ["hello.advanced.app"])
class AdvancedApplication

fun main(args: Array<String>) {
    runApplication<AdvancedApplication>(*args)
}
