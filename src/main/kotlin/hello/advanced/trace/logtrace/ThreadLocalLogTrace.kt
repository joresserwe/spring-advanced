package hello.advanced.trace.logtrace

import hello.advanced.trace.TraceId
import hello.advanced.trace.TraceStatus
import mu.KotlinLogging


class ThreadLocalLogTrace : LogTrace {

    private val log = KotlinLogging.logger {}

    private val traceIdHolder = ThreadLocal<TraceId>()

    private companion object {
        const val START_PREFIX = "-->"
        const val COMPLETE_PREFIX = "<--"
        const val EX_PREFIX = "<X-"
    }

    override fun begin(message: String): TraceStatus {
        syncTraceId()
        val traceId = traceIdHolder.get()
        val startTimeMs = System.currentTimeMillis()
        log.info { "[${traceId.id}] ${addSpace(START_PREFIX, traceId.level)}$message" }
        return TraceStatus(traceId, startTimeMs, message)
    }

    private fun syncTraceId() {
        traceIdHolder.set(traceIdHolder.get()?.createNextId() ?: TraceId())
    }

    override fun end(status: TraceStatus) {
        complete(status, null)
    }

    override fun exception(status: TraceStatus, e: Exception) {
        complete(status, e)
    }

    private fun complete(status: TraceStatus, e: Exception?) {
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
        releaseTraceId()
    }

    private fun releaseTraceId() {
        val traceId = traceIdHolder.get() ?: return
        if (traceId.isFirstLevel())
            traceIdHolder.remove()
        else
            traceIdHolder.set(traceId.createPrevId())
    }


    private fun addSpace(prefix: String, level: Int): String {
        val sb = StringBuilder()
        for (i in 0 until level) {
            sb.append(if (i == level - 1) "|$prefix" else "|    ")
        }
        return sb.toString()
    }

}
