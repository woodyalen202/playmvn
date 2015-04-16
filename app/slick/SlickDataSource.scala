package slick

import play.api.db.DB
import play.api.Play.current

import scala.slick.driver.MySQLDriver.simple._


/**
 * Created by lucien on 15/4/14.
 * slick use playframework datasource
 */
object SlickDataSource {
    //readonly datasourcce
    val readOnlyDataSource = Database.forDataSource(DB.getDataSource("readonly"))
    //ddl datasource
    val dataSource = Database.forDataSource(DB.getDataSource())
}

