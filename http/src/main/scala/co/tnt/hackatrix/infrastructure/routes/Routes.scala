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
import co.tnt.hackatrix.domain.problem.algebra.InMemoryRepository
import spray.json.DefaultJsonProtocol._
import  co.tnt.hackatrix.domain.problem.Reason


class Routes(implicit exc: ExecutionContextExecutor) {
  implicit val reasonDTO = jsonFormat1(ReasonDTO)
  implicit val problem = jsonFormat1(ProblemDTO)
  implicit val advice = jsonFormat1(Advice)

  case class Advice(description: String)

  val inmemory= new InMemoryRepository().findReasons
  val routes: Route =
    concat(
      get {
        pathPrefix("getProblems" ) {
          onSuccess(inmemory) {
            case  e: List[Reason] => complete(reasonToReasonDTO(e))
            case _       => complete(StatusCodes.NotFound)
          }
        }
      },
      post {
        path("problem") {
          entity(as[ProblemDTO]) { problem =>
            val saved: Future[Done] = saveOrder(problem)
            onComplete(saved) { done =>
              complete(Advice("Tu has recibido un consejo"))
            }
          }
        }
      }
    )

  def reasonToReasonDTO(list: List[Reason]): List[ReasonDTO] =  list.map(id=> ReasonDTO(id.id))



  def fetchItem(id: Int): Future[Option[ReasonDTO]] = Future {
    Option(ReasonDTO(id))
  }(exc)

  def saveOrder(order: ProblemDTO): Future[Done] = {
    order
    Future { Done }(exc)
  }

}
