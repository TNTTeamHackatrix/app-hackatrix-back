package co.tnt.hackatrix

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import co.tnt.hackatrix.infrastructure.routes.Routes
import co.tnt.hackatrix.infrastructure.config.{HttpConfig, ReadConfig}

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn


object Main {

  implicit val system = ActorSystem("hackatrix")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  val config =   ReadConfig.apply

  def main(args: Array[String]) {
    val bindingFuture = Http().bindAndHandle(new Routes().routes, config.readConf.http.host, 8080)
    println(s"running at ${config.readConf.http.host} ${config.readConf.http.port}")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}