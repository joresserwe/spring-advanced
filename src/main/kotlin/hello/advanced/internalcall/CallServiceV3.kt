package hello.advanced.internalcall

import mu.KotlinLogging
import org.springframework.stereotype.Component

// 구조 변경(분리)
@Component
class CallServiceV3(
    
    private val internalService: InternalService
) {
    private val log = KotlinLogging.logger {}

    fun external() {
        log.info { "call external" }
        internalService.internal()
    }
}
