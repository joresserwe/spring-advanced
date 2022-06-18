package hello.advanced.pureproxy.decorator.code

import mu.KotlinLogging

class DecoratorPatternClient(
    private val component: Component
) {
    private val log = KotlinLogging.logger {}

    fun execute() {
        val operation = component.operation()
        log.info("result=$operation")
    }
}
