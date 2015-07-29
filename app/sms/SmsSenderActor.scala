package sms

import java.net.URLEncoder

import akka.actor.{Props, Actor}
import akka.event.Logging
import play.api.libs.concurrent.Akka
import sms.SmsDataCenter._

/**
 * Created by lucien on 15/5/28.
 */
class SmsSenderActor extends Actor{

    val logger = Logging(context.system, this)

    val mysqlActor = context.actorOf(MysqlActor.props, "mysqlActor")


    import SmsSenderActor._

    override def receive: Receive = {
        case UTCMsg(taskId, phone, message) => {
            logger.info("now deal CTUMsg!")
            logger.info("phone:{},msg:{},appType:{},onlyInsert:{}", phone, message)



            println(" now deal CTUMsg! ")
        }
        case NEXMOMsg(taskId, phone, message) => {
            logger.info("now deal NEXMOMsg!")
            logger.info("phone:{},msg:{},appType:{},onlyInsert:{}", phone, message)


            println(" now deal NEXMOMsg! ")
        }
        case _ => {
            println(" some thing wrong! ")
            logger.info("some thing wrong!")
        }
    }
}

object SmsSenderActor{

    def props = Props[SmsSenderActor]

    //发送企信通信息
    def sendUTCMessage(phoneNumber:String, message:String): Unit ={
        //对phone进行处理
        //11位数字
        val phone = phoneNumber.replace("+", "");
        val realNumber =
            if (phone.length == 11)
                phone
            else if (phone.length == 13)
                phone.substring(2, 13)
            else
                "-"
        realNumber match {
            case "-" => {
                ERROR_PHONE_WRONGNUMBER
            }
            case _ => {
                val encodeMsg = URLEncoder.encode(message, "UTF8")



            }
        }

        //对message进行转码处理
//        sys.setdefaultencoding("utf-8")
//        encodeMessage = urllib.quote(message.encode('gbk'))
//        url = CTUURL % (CTUSerialNumber, CTUPassword, phone, encodeMessage)
//        # print(url)
//        result = self.http(url)
//
//        buf = cStringIO.StringIO()
//        ch = pycurl.Curl()
//        ch.setopt(pycurl.URL,url)
//        ch.setopt(pycurl.WRITEFUNCTION, buf.write)
//        # ch.setopt(pycurl.SSL_VERIFYPEER,False)
//        # ch.setopt(pycurl.SSL_VERIFYHOST,True)
//        ch.setopt(pycurl.CONNECTTIMEOUT, 5)
//        ch.setopt(pycurl.CONNECTTIMEOUT, 8)
//        ch.perform()
//        res = buf.getvalue()
//        buf.close()
//        return res
    }

    //发送NEXMO消息
    def sendNEXMOMessage: Unit ={

    }
}
