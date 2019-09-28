package co.tnt.hackatrix.domain.task

final case class Task private(name : String, isChecked : Boolean, feedback : Boolean) {

}

object Task {
  def apply(name : String, isChecked : Boolean, feedback : Boolean): Task = new Task(name, isChecked, feedback)
}
