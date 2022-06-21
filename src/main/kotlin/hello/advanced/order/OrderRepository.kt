package hello.advanced.order

import mu.KotlinLogging
import org.springframework.stereotype.Repository

@Repository
class OrderRepository {
    private val log = KotlinLogging.logger {}
    fun save(itemId: String): String {
        log.info("[orderRepository] 실행")
        //저장 로직
        check(itemId != "ex") { "예외 발생!" }
        return "ok"
    }
}
