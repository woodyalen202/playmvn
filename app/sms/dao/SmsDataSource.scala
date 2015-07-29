package sms.dao

import play.api.Play.current
import play.api.db.DB
import sms.model.SmsTables

import scala.slick.driver.MySQLDriver.simple._


/**
 * Created by lucien on 15/4/14.
 * slick use playframework datasource
 */
object SmsDataSource {
    //readonly datasourcce
    val smsdata = Database.forDataSource(DB.getDataSource("smsdata"))


    def insertTask(phoneNumber:String, message:String, taskType:Int, appType:Int): Int = {
        smsdata withSession {
            implicit session =>
                val tableQuery = SmsTables.SmsData
                val tmpdata = SmsTables.SmsDataRow(
                    0,//None
                    phoneNumber,
                    message,
                    System.currentTimeMillis(),//添加时间
                    0,//发送时间
                    0,//发送结果状态码
                    taskType,//发送通道类型码
                    appType,//发送app类型码
                    0,//是否已经发送0未发送1已发送
                    0)//是否已经发送

                val fid = (tableQuery returning tableQuery.map(_.fid)) += tmpdata
                fid
        }
    }
}

