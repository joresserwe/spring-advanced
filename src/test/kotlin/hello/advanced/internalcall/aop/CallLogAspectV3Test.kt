package hello.advanced.internalcall.aop

import hello.advanced.internalcall.CallServiceV3
import io.kotest.core.spec.style.FunSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(CallLogAspect::class)
@SpringBootTest
internal class CallLogAspectV3Test : FunSpec() {

    @Autowired
    private lateinit var callServiceV3: CallServiceV3

    init {
        this.test("external") {
            callServiceV3.external()
        }
    }
}
