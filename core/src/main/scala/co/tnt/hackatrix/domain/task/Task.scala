package co.tnt.hackatrix.domain.task

final case class Task private(name : String, isChecked : Boolean, feedback : String) {

}

object Task {
  def apply(name : String, isChecked : Boolean, feedback : String): Task = new Task(name, isChecked, feedback)
}
