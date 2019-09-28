package co.tnt.hackatrix.domain.problem.algebra

import co.tnt.hackatrix.domain.diagnostic.Diagnostic
import co.tnt.hackatrix.domain.problem.Problem

import scala.concurrent.Future

trait ProblemServices {
  def evaluateProblem(problem: Problem): Future[Diagnostic]
}
