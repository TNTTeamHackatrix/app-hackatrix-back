package co.tnt.hackatrix.domain.problem.algebra
import java.util.UUID

import co.tnt.hackatrix.domain.problem.Reason

import scala.concurrent.Future

trait ProblemRepository {

  def findReasons: Future[List[Reason]]

}

final class InMemoryRepository() extends ProblemRepository {

  private val  reasons: List[Reason] = List(Reason(id = UUID.randomUUID()),
                              Reason(id = UUID.randomUUID()),
                              Reason(id = UUID.randomUUID()),
                              )
  override def findReasons: Future[List[Reason]] = {
    Future.successful(reasons)
  }
}