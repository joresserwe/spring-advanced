package hello.advanced.member

import hello.advanced.member.annotation.ClassAop
import hello.advanced.member.annotation.MethodAop
import org.springframework.stereotype.Component

@ClassAop
@Component
class MemberServiceImpl : MemberService {

    @MethodAop("test value")
    override fun hello(param: String) = "ok"

    fun internal(param: String) = "ok"
}
