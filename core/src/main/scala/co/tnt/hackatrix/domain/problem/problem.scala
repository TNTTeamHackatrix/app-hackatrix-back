package co.tnt.hackatrix.domain.problem

final case class Problem private(reasons: List[Reason])

object Problem {
  def apply(reasons: List[Reason]): Problem = new Problem(reasons)
}



