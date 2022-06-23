package hello.advanced.exam

import hello.advanced.order.aop.exam.ExamService
import hello.advanced.order.aop.exam.aop.RetryAspect
import hello.advanced.order.aop.exam.aop.TraceAspect
import io.kotest.core.spec.style.FunSpec
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TraceAspect::class, RetryAspect::class)
@SpringBootTest
class ExamTest : FunSpec() {

    private val log = KotlinLogging.logger {}

    @Autowired
    private lateinit var examService: ExamService

    init {
        this.test("test") {

            (0..4).forEach {
                log.info { "client request i=${it}" }
                examService.request("data$it")
            }
        }
    }
}
