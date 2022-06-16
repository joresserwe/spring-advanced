package hello.advanced.trace.template.code

import mu.KotlinLogging

abstract class AbstractTemplate {

    private val log = KotlinLogging.logger {}

    fun execute() {
        val startTime = System.currentTimeMillis()

        call()

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info { "resultTime=${resultTime}" }
    }

    protected abstract fun call()
}
