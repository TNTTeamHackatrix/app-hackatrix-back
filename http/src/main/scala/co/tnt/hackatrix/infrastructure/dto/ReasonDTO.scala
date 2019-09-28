package co.tnt.hackatrix.infrastructure.dto

import java.util.UUID

final case class ReasonDTO(id:Int)


final case class ProblemDTO(reasons: List[ReasonDTO])







