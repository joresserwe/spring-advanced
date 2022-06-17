package hello.advanced.app.v3

import org.springframework.stereotype.Service

@Service
class OrderServiceV3(
    private val orderRepository: OrderRepositoryV3
) {

    fun save(itemId: String) {
        orderRepository.save(itemId)
    }
}
