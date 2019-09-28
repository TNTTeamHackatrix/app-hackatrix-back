package co.tnt.hackatrix.domain.problem.algebra

import co.tnt.hackatrix.domain.problem.Reason

import scala.concurrent.Future

trait ProblemRepository {

  def findReasons: Future[List[Reason]]
}

final class ProblemRepositoryImpl extends ProblemRepository {
  override def findReasons: Future[List[Reason]] = ???
}