package hello.advanced.jdkdynamic

import mu.KotlinLogging
import org.junit.jupiter.api.Test
import kotlin.reflect.KFunction
import kotlin.reflect.full.memberFunctions

class ReflectionTest {
    private val log = KotlinLogging.logger {}

    @Test
    fun reflection0() {
        val target = Hello()

        // 공통 로직 1 시작
        log.info { "start" }
        val result1 = target.callA()
        log.info { "result=$result1" }

        log.info { "start" }
        val result2 = target.callB()
        log.info { "result=$result2" }
    }

    @Test
    fun reflection1() {
        // Class 정보
        //val kClass = Hello::class
        val kClass = Class.forName("hello.advanced.jdkdynamic.ReflectionTest\$Hello").kotlin
        val hello = Hello()
        val functionCallA = kClass.memberFunctions.first { it.name == "callA" }
        val result1 = functionCallA.call(hello)
        println("result1=$result1")

        val functionCallB = kClass.memberFunctions.first { it.name == "callB" }
        val result2 = functionCallB.call(hello)
        println("result2=$result2")

    }

    @Test
    fun reflection2() {
        // Class 정보
        val hello = Hello()

        val kClass = Hello::class
        val functionCallA = kClass.memberFunctions.first { it.name == "callA" }
        dynamicCall(functionCallA, hello)
        val functionCallB = kClass.memberFunctions.first { it.name == "callB" }
        dynamicCall(functionCallB, hello)

    }

    private fun dynamicCall(function: KFunction<*>, target: Any) {
        log.info { "start" }
        val result2 = function.call(target)
        log.info { "result=$result2" }
    }

    inner class Hello {
        private val log = KotlinLogging.logger {}
        fun callA(): String {
            log.info { "callA" }
            return "A"
        }

        fun callB(): String {
            log.info { "callB" }
            return "B"
        }
    }
}
