package hello.advanced.internalcall.aop

import hello.advanced.internalcall.CallServiceV2
import io.kotest.core.spec.style.FunSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(CallLogAspect::class)
@SpringBootTest
internal class CallLogAspectV2Test : FunSpec() {

    @Autowired
    private lateinit var callServiceV2: CallServiceV2

    init {
        this.test("external") {
            callServiceV2.external()
        }
    }
}
