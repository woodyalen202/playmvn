package sms

import com.typesafe.config.ConfigFactory

/**
 * Created by lucien on 15/5/28.
 */
object SmsDataCenter {

    //读取sms配置文件
    lazy val smsConfig = ConfigFactory.load("sms.conf")

    //CTU配置信息
    lazy val smsCTUSerialNumber = smsConfig.getString("CTUSerialNumber")
    lazy val smsCTUPassword = smsConfig.getString("CTUPassword")
    lazy val smsCTUUrl = smsConfig.getString("CTUURL")

    //NEXMO配置
    lazy val smsNEXMOSerialNumber = smsConfig.getString("NEXMOSerialNumber")
    lazy val smsNEXMOPassword = smsConfig.getString("NEXMOPassword")
    lazy val smsNEXMOPhoneNum = smsConfig.getString("NEXMOPhoneNum")
    lazy val smsCheckInterval = smsConfig.getString("CheckInterval")

    //DB配置
    lazy val smsDBURL = smsConfig.getString("DBURL")
    lazy val smsDBUser = smsConfig.getString("DBUser")
    lazy val smsDBPwd = smsConfig.getString("DBPwd")


    /////////////////////////////////////////////
    //ERROR CODE
    /////////////////////////////////////////////
    lazy val ERROR_SYSTEM_NOMONEY = "账户余额不足"
    lazy val ERROR_SYSTEM_NOACCOUNT = "账户不存在"
    lazy val ERROR_SYSTEM_ACCOUNTSTOP = "账户已经停用"
    lazy val ERROR_SYSTEM_NOKNOW = "系统异常"

    lazy val ERROR_PHONE_NULLNUMBER = "电话号码为空"
    lazy val ERROR_PHONE_WRONGNUMBER = "电话号码不正确"
    lazy val ERROR_PHONE_LIMITNUMBER = "电话号码达到当天发送限制"
    lazy val ERROR_PHONE_LIMITNUMBER_SAME = "同一手机号，相同内容达到当天发送限制"
    lazy val ERROR_PHONE_BLACK = "发送号码为黑名单用户"

    lazy val ERROR_MSG_NULL = "内容为空或者长度小于0个字符"
    lazy val ERROR_MSG_TOLONG = "内容过长，超过350个字符"
    lazy val ERROR_MSG_SHIELD = "内容中含有屏蔽词"

    lazy val ERROR_SERVER_MD5 = "MD5校验错误"
    lazy val ERROR_SERVER_IPAUTH = "IP服务器鉴权错误"
    lazy val ERROR_SERVER_INTERFACE = "接口类型错误"
    lazy val ERROR_SERVER_SERVICE = "服务类型错误"

    /////////////////////////////////////////////
    //CASE CLASS    actor传递信息
    /////////////////////////////////////////////
    //controller --> filterActor
    case class Msg(phone:String, messsage:String, appType:String)
    //filterActor --> MysqlActor    insert task
    case class TaskMsg(phoneNumber:String, messsage:String, appType:Int, taskType:Int)
    //MysqlActor -->   smsActor
    //根据phone区分使用的,国内使用企信通(UTC),国外使用NEXMO
    case class UTCMsg(taskId:Long, phone:String, messsage:String)
    case class NEXMOMsg(taskId:Long, phone:String, messsage:String)
    //smsActor -->MysqlActor    update task
    case class UpdateMsg(taskId:Long, statusCode:Int)
    //返回消息
    //filter --> controller
    case class ResultMsg(success:Boolean, statusCode:Int, msg:String)

    //批量发送短信


    def main(args: Array[String]) {
        println("smsCTUSerialNumber = " + smsCTUSerialNumber)
        println("smsCTUPassword = " + smsCTUPassword)
        println("smsCTUUrl = " + smsCTUUrl)

        smsCTUUrl

        println("smsNEXMOSerialNumber = " + smsNEXMOSerialNumber)
        println("smsNEXMOPassword = " + smsNEXMOPassword)
        println("smsNEXMOPhoneNum = " + smsNEXMOPhoneNum)
        println("smsCheckInterval = " + smsCheckInterval)

        println("smsDBURL = " + smsDBURL)
        println("smsDBUser = " + smsDBUser)
        println("smsDBPwd = " + smsDBPwd)
    }

}
