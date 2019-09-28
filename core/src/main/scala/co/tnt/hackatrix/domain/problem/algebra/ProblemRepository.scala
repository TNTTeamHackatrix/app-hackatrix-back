package co.tnt.hackatrix.domain.problem.algebra
import java.util.UUID

import co.tnt.hackatrix.domain.diagnostic.Diagnostic
import co.tnt.hackatrix.domain.problem.{Problem, Reason}
import co.tnt.hackatrix.domain.task.Task

import scala.concurrent.Future

trait ProblemRepository {

  def findReasons: Future[List[Reason]]
  def evaluateProblem(problem: Problem): Future[Diagnostic]
}

final class InMemoryRepository() extends ProblemRepository {

  private val  reasons = List(Reason(id = 1),
                              Reason(id = 2),
                              Reason(id = 3),
                              )

  private val fixedDiagnostic = Diagnostic( List(Task(name = "task1", isChecked = false, feedback = "")), "Some profile")
  override def findReasons: Future[List[Reason]] = {
    Future.successful(reasons)
  }

  // We have to evaluate user problem, for now we send fixed diagnostic!
  override def evaluateProblem(problem: Problem): Future[Diagnostic] =
    Future.successful(
    fixedDiagnostic
  )
}