package hello.advanced.trace.strategy

import hello.advanced.trace.strategy.code.template.TimeLogTemplate
import mu.KotlinLogging
import org.junit.jupiter.api.Test

class TemplateCallbackTest {

    private val log = KotlinLogging.logger {}

    @Test
    fun callbackV1() {
        val template = TimeLogTemplate()
        template.execute { log.info { "비즈니스 로직1 실행" } }
        template.execute { log.info { "비즈니스 로직 2실행" } }
    }

}
