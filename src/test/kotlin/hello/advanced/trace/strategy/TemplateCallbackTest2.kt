package hello.advanced.trace.strategy

class TemplateCallbackTest2 {

    fun interface IntPredicate {
        fun accept(i: Int): Boolean
    }

    val isEven = IntPredicate { it % 2 == 0 }
}
