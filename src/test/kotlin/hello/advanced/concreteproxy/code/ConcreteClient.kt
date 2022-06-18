package hello.advanced.concreteproxy.code

class ConcreteClient(
    private val concreteLogic: ConcreteLogic
) {

    fun execute() {
        concreteLogic.operation()
    }
}
