package hello.advanced.app.v2

open class OrderServiceV2(
    private val orderRepository: OrderRepositoryV2
) {

    open fun save(itemId: String) {
        orderRepository.save(itemId)
    }
}
