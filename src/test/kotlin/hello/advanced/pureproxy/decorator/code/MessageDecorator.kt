package hello.advanced.pureproxy.decorator.code

import mu.KotlinLogging

class MessageDecorator(
    private val component: Component
) : Component {
    private val log = KotlinLogging.logger {}

    override fun operation(): String {
        log.info { "MessageDecorator 실행" }
        val operation = component.operation()
        val decoResult = "*****${operation}*****"
        log.info {
            "MessageDecorator 적용 전=$operation 적용 후=$decoResult}"
        }
        return decoResult
    }
}
