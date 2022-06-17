package hello.advanced.app.v2

class OrderServiceV2(
    private val orderRepository: OrderRepositoryV2
) {

    fun save(itemId: String) {
        orderRepository.save(itemId)
    }
}
