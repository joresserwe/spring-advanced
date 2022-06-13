package hello.advanced.trace.hellotrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class HelloTraceV1 {

    private val log = KotlinLogging.logger {}

    private companion object {
        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EX_PREFIX = "<X-"
    }

    fun begin(message: String): TraceStatus {
        val traceId = TraceId()
        val startTimeMs = System.currentTimeMillis()
        log.info { "[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)}$message" }
        return TraceStatus(traceId, startTimeMs, message)
    }

    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        for (i in 1 until level) {
            sb.append(if (i == level - 1) "|$prefix" else "|    ")
        }
        return sb.toString()
    }

    fun end(status: TraceStatus) {
        complete(status, null)
    }

    fun exception(status: TraceStatus, e: Exception) {
        complete(status, e)
    }

    fun complete(status: TraceStatus, e: Exception?) {
        val resultTimeMs = System.currentTimeMillis() - status.startTimeMs
        val traceId = status.traceId
        e?.let {
            log.info {
                "[${traceId.id}] ${
                    addSpace(
                        EX_PREFIX,
                        traceId.level
                    )
                }${status.message} time=${resultTimeMs}ms ex=$e"
            }
        } ?: run {
            log.info {
                "[${traceId.id}] ${
                    addSpace(
                        COMPLETE_PREFIX,
                        traceId.level
                    )
                }${status.message} time=${resultTimeMs}ms"
            }
        }
    }
}
