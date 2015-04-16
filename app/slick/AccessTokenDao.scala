package slick

import slick.model.Tables
import scala.slick.driver.MySQLDriver.simple._


/**
 * Created by lucien on 15/4/14.
 */
class AccessTokenDao {

    def countTotalNum : Int = {
        SlickDataSource.readOnlyDataSource withSession {
            implicit session => {
                Tables.AccessToken.list(session).size
            }
        }
    }

}


object AccessTokenDao{

    def apply() : AccessTokenDao = {
        new AccessTokenDao()
    }
}