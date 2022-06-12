package hello.advanced.v0

import org.springframework.stereotype.Repository
import kotlin.concurrent.thread

@Repository
class OrderRepositoryV0 {

    fun save(itemId: String) {
        if (itemId == "ex") {
            throw IllegalStateException("예외 발생!!!")
        }
        sleep(1000)
    }

    private fun sleep(millis: Long) {
        thread { Thread.sleep(millis) }
    }
}
