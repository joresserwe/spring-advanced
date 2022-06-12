package hello.advanced.trace

import java.util.*

// static
private fun createId() = UUID.randomUUID().toString().substring(0..7)

class TraceId(
    var id: String = createId(),
    var level: Int = 0
) {
    fun createNextId() = TraceId(id, level + 1)
    fun createPrevId() = TraceId(id, level - 1)
    fun isFirstLevel() = level == 0
}

