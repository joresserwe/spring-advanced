package hello.advanced.internalcall

import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class InternalService {
    
    private val log = KotlinLogging.logger {}

    fun internal() {
        log.info { "call internal" }
    }
}
