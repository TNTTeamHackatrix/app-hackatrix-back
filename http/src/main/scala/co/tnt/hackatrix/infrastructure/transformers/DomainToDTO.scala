package co.tnt.hackatrix.infrastructure.transformers

import co.tnt.hackatrix.domain.problem.Reason
import co.tnt.hackatrix.infrastructure.dto.ReasonDTO

class DomainToDTO {

  def ReasonToReasonDTO(list: List[Reason]): List[ReasonDTO] =  list.map(id=> ReasonDTO(id.id))

}
