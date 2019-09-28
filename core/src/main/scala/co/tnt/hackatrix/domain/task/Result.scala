package co.tnt.hackatrix.domain.task

final case class Result(score: Double) {

}

object Result {
  def apply(score : Double): Result = new Result(score)
}
