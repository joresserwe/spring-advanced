package hello.advanced.trace.strategy.code.strategy

import mu.KotlinLogging

class StrategyLogic2 : Strategy {

    private val log = KotlinLogging.logger {}

    override fun call() {
        log.info { "로직 2 실행" }
    }
}
