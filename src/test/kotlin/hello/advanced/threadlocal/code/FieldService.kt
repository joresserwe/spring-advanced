package hello.advanced.threadlocal.code

import mu.KotlinLogging

class FieldService {
    private val log = KotlinLogging.logger {}

    private var nameStore: String? = null

    fun logic(name: String): String? {
        log.info { "저장 name=$name -> nameStore=$nameStore" }
        nameStore = name
        sleep(1000)
        log.info { "조회 nameStore=$nameStore" }
        return nameStore
    }

    private fun sleep(millis: Long) {
        Thread.sleep(1000)
    }
}
