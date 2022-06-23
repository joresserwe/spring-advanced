package hello.advanced.order.aop.exam

import hello.advanced.order.aop.exam.annotation.Trace
import org.springframework.stereotype.Service

@Service
class ExamService(private val examRepository: ExamRepository) {

    @Trace
    fun request(itemId: String) {
        examRepository.save(itemId)
    }

}
