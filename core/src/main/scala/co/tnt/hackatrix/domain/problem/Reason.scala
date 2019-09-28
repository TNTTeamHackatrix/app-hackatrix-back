package co.tnt.hackatrix.domain.problem

final case class Reason private(id:Int)

object Reason {
  def apply(id: Int): Reason = new Reason(id)
}
