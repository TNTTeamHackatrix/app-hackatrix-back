package co.tnt.hackatrix.infrastructure.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, concat, entity, get, onComplete, onSuccess, path, pathPrefix, post}
import akka.http.scaladsl.server.Route
import co.tnt.hackatrix.infrastructure.dto.{ProblemDTO, ReasonDTO}
import spray.json.DefaultJsonProtocol.{jsonFormat1, jsonFormat2}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import co.tnt.hackatrix.domain.diagnostic.Diagnostic
import co.tnt.hackatrix.domain.problem.algebra.{InMemoryRepository, ProblemServicesImpl}
import spray.json.DefaultJsonProtocol._
import co.tnt.hackatrix.domain.problem.Reason
import co.tnt.hackatrix.domain.task.Task
import co.tnt.hackatrix.infrastructure.transformers.{DTOtoDomain, DomainToDTO}

import scala.concurrent.ExecutionContextExecutor


class Routes(implicit exc: ExecutionContextExecutor) {

  implicit val task = jsonFormat3(Task)
  implicit val diagnostic = jsonFormat2(Diagnostic)
  implicit val reasonDTO = jsonFormat1(ReasonDTO)
  implicit val problem = jsonFormat1(ProblemDTO)
  val repository= new InMemoryRepository()
  val problemService =  ProblemServicesImpl(repository)
  val transformerDTO = new DTOtoDomain()
  val transformerDomain = new DomainToDTO()
  val routes: Route =
    concat(
      get {
        pathPrefix("getProblems" ) {
          onSuccess(repository.findReasons) {
            case  e: List[Reason] => complete(transformerDomain.ReasonToReasonDTO(e))
            case _       => complete(StatusCodes.NotFound)
          }
        }
      },
      post {
        path("problem") {
          entity(as[ProblemDTO]) { problem =>
            val saved = problemService.evaluateProblem(transformerDTO.ProblemDTOtoProblem(problem))
            onSuccess(saved) {
              case  e: Diagnostic => complete(e)
              case _       => complete(StatusCodes.NotFound)
            }
          }
        }
      }
    )
}
