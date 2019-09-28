package co.tnt.hackatrix.infrastructure.repository.config

final case class DBSettings(
                           address: String,
                           driver: String,
                           user: String,
                           password: String
                         )