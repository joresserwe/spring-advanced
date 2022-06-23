package hello.advanced.internalcall

import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class CallServiceV0 {
    private val log = KotlinLogging.logger {}

    fun external() {
        log.info { "call external" }
        internal() // 내부 Method 호출(this.internal())
    }

    fun internal() {
        log.info { "call internal" }
    }
}
