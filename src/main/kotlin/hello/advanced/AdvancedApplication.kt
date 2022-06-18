package hello.advanced

import hello.advanced.config.v1_proxy.InterfaceProxyConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

//@Import(AppV1Config::class, AppV2Config::class)
@Import(InterfaceProxyConfig::class)
@SpringBootApplication(scanBasePackages = ["hello.advanced.app"])
class AdvancedApplication

fun main(args: Array<String>) {
    runApplication<AdvancedApplication>(*args)
}
