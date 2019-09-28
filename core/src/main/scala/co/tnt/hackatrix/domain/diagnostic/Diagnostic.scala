package co.tnt.hackatrix.domain.diagnostic

final case class Diagnostic private()

object Diagnostic {
  def apply: Diagnostic = new Diagnostic()
}
