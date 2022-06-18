package hello.advanced.pureproxy.decorator

import hello.advanced.pureproxy.decorator.code.DecoratorPatternClient
import hello.advanced.pureproxy.decorator.code.MessageDecorator
import hello.advanced.pureproxy.decorator.code.RealComponent
import hello.advanced.pureproxy.decorator.code.TimeDecorator
import mu.KotlinLogging
import org.junit.jupiter.api.Test

internal class DecoratorPatternTest {
    private val log = KotlinLogging.logger {}

    @Test
    fun noDecorator() {
        val realComponent = RealComponent()
        val decoratorPatternClient = DecoratorPatternClient(realComponent)
        decoratorPatternClient.execute()
    }

    @Test
    fun decorator1() {
        val realComponent = RealComponent()
        val messageDecorator = MessageDecorator(realComponent)
        val decoratorPatternClient = DecoratorPatternClient(messageDecorator)
        decoratorPatternClient.execute()
    }

    @Test
    fun decorator2() {
        val realComponent = RealComponent()
        val messageDecorator = MessageDecorator(realComponent)
        val timeDecorator = TimeDecorator(messageDecorator)
        val decoratorPatternClient = DecoratorPatternClient(timeDecorator)
        decoratorPatternClient.execute()
    }
}
