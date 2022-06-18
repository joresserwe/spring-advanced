package hello.advanced.pureproxy.decorator.code

import mu.KotlinLogging

class TimeDecorator(
    private val component: Component
) : Component {
    private val log = KotlinLogging.logger {}

    override fun operation(): String {
        log.info { "TimeDecorator 실행" }
        val startTime = System.currentTimeMillis()

        val operation = component.operation()
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info { "TimeDecorator 종료 resultTime=${resultTime}ms" }
        
        return operation
    }
}
