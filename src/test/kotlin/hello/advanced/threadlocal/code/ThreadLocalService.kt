package hello.advanced.threadlocal.code

import mu.KotlinLogging

class ThreadLocalService {
    private val log = KotlinLogging.logger {}

    private val nameStore = ThreadLocal<String>()

    fun logic(name: String): String {
        log.info { "저장 name=$name -> nameStore=${nameStore.get()}" }
        nameStore.set(name)
        sleep(1000)
        log.info { "조회 nameStore=${nameStore.get()}" }
        return nameStore.get()
    }

    private fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}
