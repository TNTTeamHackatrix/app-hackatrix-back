package co.tnt.hackatrix.infrastructure.repository.doobie

import cats.effect.IO
import co.tnt.hackatrix.infrastructure.repository.config.DBSettings
import doobie._
import doobie.util.transactor.Transactor.Aux


object Transactor1 {

  import scala.concurrent.ExecutionContext

  implicit val cs = IO.contextShift(ExecutionContext.global)
  def create(db: DBSettings): Aux[IO, Unit] =
    Transactor.fromDriverManager[IO](url = db.address, driver = db.driver, user = "", pass = "")
}
