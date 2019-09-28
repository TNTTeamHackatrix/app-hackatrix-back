package co.tnt.hackatrix.domain.diagnostic

final case class Plan private ()

object Plan{
  def apply: Plan = new Plan()
}
