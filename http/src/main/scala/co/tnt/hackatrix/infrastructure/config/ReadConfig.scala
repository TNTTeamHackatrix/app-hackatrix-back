package co.tnt.hackatrix.infrastructure.config
import co.tnt.hackatrix.infrastructure.config.HttpConfig
import pureconfig.ConfigReader.Result
import pureconfig.ConfigSource
import pureconfig._
import pureconfig.generic.auto._

case class AppConf(http: HttpConfig)

final class ReadConfig private {
  private val appConf: Result[AppConf] =  ConfigSource.default.load[AppConf]
  def readConf: AppConf =
    appConf.getOrElse(throw new Exception(""))
}

object ReadConfig {
  def apply: ReadConfig = new ReadConfig()
}