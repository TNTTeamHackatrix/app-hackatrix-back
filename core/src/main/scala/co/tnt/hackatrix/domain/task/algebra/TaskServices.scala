package co.tnt.hackatrix.domain.task.algebra

import co.tnt.hackatrix.domain.task.{Result, Task}

import scala.concurrent.Future

trait TaskServices {
  def checkProgress (task : List[Task]): Future[Result]

}
