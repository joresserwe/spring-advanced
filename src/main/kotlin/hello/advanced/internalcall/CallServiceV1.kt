package hello.advanced.internalcall

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CallServiceV1 {
    private val log = KotlinLogging.logger {}

    private lateinit var callServiceV1: CallServiceV1

    @Autowired
    fun setCallServiceV1(callServiceV1: CallServiceV1) {
        log.info { "callServiceV1=${callServiceV1::class}" }
        this.callServiceV1 = callServiceV1
    }

    fun external() {
        log.info { "call external" }
        callServiceV1.internal() // Proxy 호출
    }

    fun internal() {
        log.info { "call internal" }
    }
}
