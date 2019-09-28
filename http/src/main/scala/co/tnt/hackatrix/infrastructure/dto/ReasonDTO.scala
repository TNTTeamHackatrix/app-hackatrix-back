package co.tnt.hackatrix.infrastructure.dto

final case class ReasonDTO(id: Int, value: Boolean)

final case class ProblemDTO(reasons: List[ReasonDTO])




