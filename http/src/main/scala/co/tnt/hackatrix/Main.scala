import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import co.tnt.hackatrix.infrastructure.dto.{ProblemDTO, ReasonDTO}
import co.tnt.hackatrix.infrastructure.routes.Routes
import scala.concurrent.ExecutionContextExecutor
// for JSON serialization/deserialization following dependency is required:
// "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.7"
import spray.json.DefaultJsonProtocol._

import scala.io.StdIn


object Main {

  implicit val system = ActorSystem("hackatrix")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  implicit val reason = jsonFormat2(ReasonDTO)
  implicit val problem = jsonFormat1(ProblemDTO)
  val config: AppConfReader =  AppConfReader.apply

  def main(args: Array[String]) {
    val bindingFuture = Http().bindAndHandle(new Routes(executionContext).routes, config.readConf.http.host, 8080)
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}