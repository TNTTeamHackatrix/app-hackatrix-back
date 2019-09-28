package co.tnt.hackatrix.domain.problem

import java.util.UUID

final case class Reason private(id:UUID){



}

object Reason {
  def apply(id: UUID): Reason = new Reason(id)
}
