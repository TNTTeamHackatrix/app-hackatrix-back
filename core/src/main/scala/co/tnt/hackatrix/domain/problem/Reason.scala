package co.tnt.hackatrix.domain.problem

final case class Reason private(description: String, value: Boolean){

}

object Reason {
  def apply(description: String, value: Boolean): Reason = new Reason(description, value)
}
