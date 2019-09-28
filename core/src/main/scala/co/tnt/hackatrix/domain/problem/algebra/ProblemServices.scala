package co.tnt.hackatrix.domain.problem.algebra

import co.tnt.hackatrix.domain.diagnostic.Diagnostic
import co.tnt.hackatrix.domain.problem.{Problem, Reason}

import scala.concurrent.Future

case class ProblemId()


trait ProblemServices {
  def evaluateProblem(problem: Problem): Future[Diagnostic]
  def getProblemsId(): Future[List[Reason]]
}

final class ProblemServicesImpl private()(implicit repo: ProblemRepository) extends ProblemServices {
  override def evaluateProblem(problem: Problem): Future[Diagnostic] = ???

  override def getProblemsId(): Future[List[Reason]] = ???
}

object ProblemServicesImpl {
  def apply(implicit repository: ProblemRepository): ProblemServicesImpl = new ProblemServicesImpl()
}