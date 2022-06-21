package hello.advanced.order

import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository
) {
    private val log = KotlinLogging.logger {}
    fun orderItem(itemId: String) {
        log.info("[orderService] 실행")
        orderRepository.save(itemId)
    }
}
