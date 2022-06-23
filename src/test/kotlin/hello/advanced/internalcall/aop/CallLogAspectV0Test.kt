package hello.advanced.internalcall.aop

import hello.advanced.internalcall.CallServiceV0
import io.kotest.core.spec.style.FunSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(CallLogAspect::class)
@SpringBootTest
internal class CallLogAspectV0Test : FunSpec() {

    @Autowired
    private lateinit var callServiceV0: CallServiceV0

    init {
        this.test("external") {
            callServiceV0.external()
        }

        this.test("internal") {
            callServiceV0.internal()
        }


    }
}
