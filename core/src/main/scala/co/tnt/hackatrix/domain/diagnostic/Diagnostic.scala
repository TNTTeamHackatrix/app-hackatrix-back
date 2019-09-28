package co.tnt.hackatrix.domain.diagnostic

import co.tnt.hackatrix.domain.task.Task

final case class Diagnostic private(tasks: List[Task], profile: String)

object Diagnostic {
  def apply(tasks: List[Task], profile: String): Diagnostic = new Diagnostic(tasks, profile)
}
