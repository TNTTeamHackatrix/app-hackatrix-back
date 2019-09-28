package co.tnt.hackatrix.infrastructure.routes

import akka.http.scaladsl.server.Directives._
import akka.Done
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{as, complete, concat, entity, get, onComplete, onSuccess, path, pathPrefix, post}
import akka.http.scaladsl.server.Route
import co.tnt.hackatrix.infrastructure.dto.{ProblemDTO, ReasonDTO}
import spray.json.DefaultJsonProtocol.{jsonFormat1, jsonFormat2}

import scala.concurrent.{ExecutionContextExecutor, Future}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes.Success
import co.tnt.hackatrix.domain.diagnostic.Diagnostic
import co.tnt.hackatrix.domain.problem.algebra.{InMemoryRepository, ProblemServicesImpl}
import spray.json.DefaultJsonProtocol._
import co.tnt.hackatrix.domain.problem.{Problem, Reason}
import co.tnt.hackatrix.domain.task.Task


class Routes(implicit exc: ExecutionContextExecutor) {

  implicit val task = jsonFormat3(Task)

  implicit val diagnostic = jsonFormat2(Diagnostic)

  implicit val reasonDTO = jsonFormat1(ReasonDTO)
  implicit val problem = jsonFormat1(ProblemDTO)
  implicit val advice = jsonFormat1(Advice)

  case class Advice(description: String)

  val inmemory= new InMemoryRepository()
  val problemService =  ProblemServicesImpl(inmemory)

  val routes: Route =
    concat(
      get {
        pathPrefix("getProblems" ) {
          onSuccess(inmemory.findReasons) {
            case  e: List[Reason] => complete(ReasonToReasonDTO(e))
            case _       => complete(StatusCodes.NotFound)
          }
        }
      },
      post {
        path("problem") {
          entity(as[ProblemDTO]) { problem =>
            val saved = problemService.evaluateProblem(ProblemDTOtoProblem(problem))
            onSuccess(saved) {
              case  e: Diagnostic => complete(e)
              case _       => complete(StatusCodes.NotFound)
            }
          }
        }
      }
    )

  def ReasonToReasonDTO(list: List[Reason]): List[ReasonDTO] =  list.map(id=> ReasonDTO(id.id))

  def ReasonDTOToreason(list: List[ReasonDTO]): List[Reason] =  list.map(id=> Reason(id.id))

  def ProblemDTOtoProblem(problem: ProblemDTO): Problem = Problem(ReasonDTOToreason(problem.reasons))

  def ProblemDTOtoProblem(problem: Problem): ProblemDTO = ProblemDTO(ReasonToReasonDTO(problem.reasons))




  def fetchItem(id: Int): Future[Option[ReasonDTO]] = Future {
    Option(ReasonDTO(id))
  }(exc)

  def saveOrder(order: ProblemDTO): Future[Done] = {
    order
    Future { Done }(exc)
  }

}
