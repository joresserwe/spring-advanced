package hello.advanced.order.aop.exam

import hello.advanced.order.aop.exam.annotation.Retry
import hello.advanced.order.aop.exam.annotation.Trace
import org.springframework.stereotype.Repository

@Repository
class ExamRepository {

    companion object {
        private var seq = 0
    }

    /**
     * 5번에 1번 실패하는 요청
     */
    @Trace
    @Retry
    fun save(itemId: String): String {
        seq++
        check(seq % 5 != 0) { "예외 발생" }
        return "ok"
    }

}
