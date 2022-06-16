package hello.advanced.trace.template

import hello.advanced.trace.template.code.AbstractTemplate
import hello.advanced.trace.template.code.SubClassLogic1
import hello.advanced.trace.template.code.SubClassLogic2
import mu.KotlinLogging
import org.junit.jupiter.api.Test


internal class TemplateMethodTest {

    private val log = KotlinLogging.logger {}

    @Test
    fun templateMethodV0() {
        logic1()
        logic2()
    }

    private fun logic1() {
        val startTime = System.currentTimeMillis()
        log.info { "비지니스 로직 1 실행" }

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info { "resultTime=${resultTime}" }
    }

    private fun logic2() {
        val startTime = System.currentTimeMillis()
        log.info { "비지니스 로직 2 실행" }

        val endTime = System.currentTimeMillis()
        val resultTime = endTime - startTime
        log.info { "resultTime=${resultTime}" }
    }

    @Test
    fun templateMethodV1() {
        val template1 = SubClassLogic1()
        val template2 = SubClassLogic2()

        template1.execute()
        template2.execute()
    }

    @Test
    // 익명 내부 Class 사용
    fun templateMethodV2() {
        val template1 = object : AbstractTemplate() {
            override fun call() {
                log.info { "비지니스 로직 1 실행" }
            }
        }
        template1.execute()
        log.info { "클래스 이름1=${template1.javaClass}" }
        val template2 = object : AbstractTemplate() {
            override fun call() {
                log.info { "비지니스 로직 2 실행" }
            }
        }
        template2.execute()
        log.info { "클래스 이름2=${template2.javaClass}" }
    }
}
