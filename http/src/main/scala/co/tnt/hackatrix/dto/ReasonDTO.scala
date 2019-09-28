package co.tnt.hackatrix.dto

final case class ReasonDTO(description: String, value: Boolean)

final case class ProblemDTO(reasons: List[ReasonDTO])




