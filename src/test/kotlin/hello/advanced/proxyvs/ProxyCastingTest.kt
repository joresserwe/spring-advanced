package hello.advanced.proxyvs

import hello.advanced.member.MemberService
import hello.advanced.member.MemberServiceImpl
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import org.springframework.aop.framework.ProxyFactory

internal class ProxyCastingTest : FunSpec({

    test("jdkProxy") {
        val target = MemberServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = false // JDK 동적 프록시

        // Proxy를 Interface로 Casting -> 성공
        val proxy = proxyFactory.proxy as MemberService

        // JDK Dynamic Proxy를 Concrete Class로 Casting 불가 (ClassCastException)
        shouldThrow<ClassCastException> { proxyFactory.proxy as MemberServiceImpl }
    }

    test("cglib") {
        val target = MemberServiceImpl()
        val proxyFactory = ProxyFactory(target)
        proxyFactory.isProxyTargetClass = true // JDK 동적 프록시

        // Proxy를 Interface로 Casting -> 성공
        val proxy = proxyFactory.proxy as MemberService
        // JConcrete Class로 Casting 성공
        val castingMemberService = proxyFactory.proxy as MemberServiceImpl
    }

})
