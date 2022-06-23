package hello.advanced.internalcall.aop

import hello.advanced.internalcall.CallServiceV1
import io.kotest.core.spec.style.FunSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(CallLogAspect::class)
@SpringBootTest
internal class CallLogAspectV1Test : FunSpec() {

    @Autowired
    private lateinit var callServiceV1: CallServiceV1

    init {
        this.test("external") {
            callServiceV1.external()
        }
    }
}
