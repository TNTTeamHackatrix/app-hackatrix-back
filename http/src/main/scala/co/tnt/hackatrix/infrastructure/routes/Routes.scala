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
import spray.json.DefaultJsonProtocol._



class Routes(exc: ExecutionContextExecutor) {
  implicit val reason = jsonFormat2(ReasonDTO)
  implicit val problem = jsonFormat1(ProblemDTO)
  implicit val advice = jsonFormat1(Advice)

  case class Advice(description: String)

  val routes: Route =
    concat(
      get {
        pathPrefix("g" / IntNumber) { id =>
          val maybeItem: Future[Option[ReasonDTO]] = fetchItem(true, id)
          onSuccess(maybeItem) {
            case Some(item) => complete(item)
            case None       => complete(StatusCodes.NotFound)
          }
        }
      },
      post {
        path("find_result") {
          entity(as[ProblemDTO]) { problem =>
            val saved: Future[Done] = saveOrder(problem)
            onComplete(saved) { done =>
              complete(Advice("Tu has recibido un consejo"))
            }
          }
        }
      }
    )

  def fetchItem(value: Boolean, id: Int): Future[Option[ReasonDTO]] = Future {
    Option(ReasonDTO(id, value))
  }(exc)

  def saveOrder(order: ProblemDTO): Future[Done] = {
    order
    Future { Done }(exc)
  }

}
