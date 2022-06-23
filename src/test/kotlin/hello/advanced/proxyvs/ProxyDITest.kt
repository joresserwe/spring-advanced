package hello.advanced.proxyvs

import hello.advanced.member.MemberService
import hello.advanced.member.MemberServiceImpl
import hello.advanced.proxyvs.code.ProxyDIAspect
import io.kotest.core.spec.style.FunSpec
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(ProxyDIAspect::class)
@SpringBootTest(properties = ["spring.aop.proxy-target-class=false"]) // JDK Dynamic Proxy
//@SpringBootTest(properties = ["spring.aop.proxy-target-class=true"]) // CGLIB
class ProxyDITest : FunSpec() {

    private val log = KotlinLogging.logger {}

    @Autowired
    private lateinit var memberService: MemberService

    @Autowired
    private lateinit var memberServiceImpl: MemberServiceImpl

    init {
        this.test("go") {
            log.info { "memberService class=${memberService::class}" }
            log.info { "memberServiceImpl class=${memberServiceImpl::class}" }
            memberServiceImpl.hello("hello")
        }

    }
}
