package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.strategy.ContextV1
import hello.advanced.trace.strategy.code.strategy.Strategy
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class ContextV1Test {

    private val log = KotlinLogging.logger {}

    @Test
    fun strategyV1() {
        val strategyLogic1 = StrategyLogic1()
        val contextV1 = ContextV1(strategyLogic1)
        contextV1.execute()

        val strategyLogic2 = StrategyLogic2()
        val contextV2 = ContextV1(strategyLogic2)
        contextV2.execute()
    }

    @Test
    fun strategyV2() {
        val strategyLogic1 = object : Strategy {
            override fun call() {
                log.info { "비지니스 로직 1 실행" }
            }
        }
        val contextV1 = ContextV1(strategyLogic1)
        contextV1.execute()

        val strategyLogic2 = object : Strategy {
            override fun call() {
                log.info { "비지니스 로직 2 실행" }
            }
        }
        val contextV2 = ContextV1(strategyLogic2)
        contextV2.execute()
    }

    @Test
    fun strategyV3() {
        val contextV1 = ContextV1(object : Strategy {
            override fun call() {
                log.info { "비지니스 로직 1 실행" }
            }
        })
        contextV1.execute()

        val contextV2 = ContextV1(object : Strategy {
            override fun call() {
                log.info { "비지니스 로직 2 실행" }
            }
        })
        contextV2.execute()
    }

    @Test
    fun strategyV4() {
        val contextV1 = ContextV1 { log.info { "비지니스 로직 1 실행" } }
        contextV1.execute()

        val contextV2 = ContextV1 { log.info { "비지니스 로직 2 실행" } }
        contextV2.execute()
    }

}
