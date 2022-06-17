package hello.advanced.trace.strategy.code.strategy

import mu.KotlinLogging

/**
 * Field에 Strategy 보관
 */
class ContextV2 {

    private val log = KotlinLogging.logger {}

    fun execute(strategy: Strategy) {
        val startTime = System.currentTimeMillis()
        strategy.call() // 위임
        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info { "resultTime=${resultTime}" }
    }
}
