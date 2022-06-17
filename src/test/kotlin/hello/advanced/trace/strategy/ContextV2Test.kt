package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.strategy.ContextV2
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class ContextV2Test {

    private val log = KotlinLogging.logger {}

    @Test
    fun strategyV1() {
        val contextV2 = ContextV2()
        contextV2.execute { log.info("비지니스 로직 1 실행") }
        contextV2.execute { log.info("비지니스 로직 2 실행") }
    }
}
