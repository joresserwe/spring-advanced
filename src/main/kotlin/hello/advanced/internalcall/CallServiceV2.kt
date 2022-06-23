package hello.advanced.internalcall

import mu.KotlinLogging
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Component

@Component
class CallServiceV2(
    //private val applicationContext: ApplicationContext
    private val callServiceProvider: ObjectProvider<CallServiceV2>
) {
    private val log = KotlinLogging.logger {}

    fun external() {
        log.info { "call external" }
        //val callServiceV2 = applicationContext.getBean(CallServiceV2)
        val callServiceV2 = callServiceProvider.`object`
        callServiceV2.internal()
    }

    fun internal() {
        log.info { "call internal" }
    }
}
