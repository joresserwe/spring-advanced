package hello.advanced.trace.template.code

import mu.KotlinLogging

class SubClassLogic1 : AbstractTemplate() {
    private val log = KotlinLogging.logger {}

    override fun call() {
        log.info("비즈니스 로직 1 실행")
    }
}
