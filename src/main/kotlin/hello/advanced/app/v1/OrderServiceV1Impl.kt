package hello.advanced.app.v1

class OrderServiceV1Impl(
    private val orderRepository: OrderRepositoryV1
) : OrderServiceV1 {

    override fun save(itemId: String) {
        orderRepository.save(itemId)
    }
}
