package hello.advanced.app.v2

class OrderRepositoryV2 {
    fun save(itemId: String) {
        if (itemId == "ex") {
            throw IllegalStateException("예외 발생!")
        }
        Thread.sleep(1000)
    }
}
