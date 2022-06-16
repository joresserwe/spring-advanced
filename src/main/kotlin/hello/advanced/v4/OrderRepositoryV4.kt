package hello.advanced.v4

import hello.advanced.trace.logtrace.LogTrace
import hello.advanced.trace.template.AbstractTemplate
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryV4(
    private val trace: LogTrace
) {
    fun save(itemId: String) {
        object : AbstractTemplate(trace) {
            override fun call() {
                sleep(1000)
            }
        }.execute("OrderRepository.save()")
    }

    private fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}
