package hello.advanced.threadlocal

import hello.advanced.threadlocal.code.ThreadLocalService
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import kotlin.concurrent.thread

class ThreadLocalServiceTest {
    private val log = KotlinLogging.logger {}

    private val fieldService = ThreadLocalService()

    @Test
    fun field() {
        log.info { "main start" }
        val threadA = thread(name = "tread-A", start = false) { fieldService.logic("userA") }
        val threadB = thread(name = "tread-B", start = false) { fieldService.logic("userB") }
        threadA.start()
        //sleep(2000)
        sleep(100)
        threadB.start()

        sleep(3000) // 메인 쓰레드 종료 대기
        log.info("main exit")
    }

    private fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}
