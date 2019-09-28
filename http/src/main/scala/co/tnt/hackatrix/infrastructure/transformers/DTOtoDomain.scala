package co.tnt.hackatrix.infrastructure.transformers

import co.tnt.hackatrix.domain.problem.{Problem, Reason}
import co.tnt.hackatrix.infrastructure.dto.{ProblemDTO, ReasonDTO}

class DTOtoDomain {
  def ReasonDTOToreason(list: List[ReasonDTO]): List[Reason] =  list.map(id=> Reason(id.id))

  def ProblemDTOtoProblem(problem: ProblemDTO): Problem = Problem(ReasonDTOToreason(problem.reasons))
}
