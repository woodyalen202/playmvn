package sms

import akka.actor.{Props, Actor}
import akka.actor.Actor.Receive
import akka.event.Logging
import sms.SmsDataCenter._
import sms.dao.SmsDataSource

/**
 * Created by lucien on 15/5/28.
 */
class MysqlActor  extends Actor{

    val logger = Logging(context.system, this)

    val smsActor = context.actorOf(SmsSenderActor.props, "smsActor")

    import MysqlActor._

    override def receive: Receive = {
        case TaskMsg(phoneNumber, message, appType, taskType) => {
            //添加到数据库中
            val fid = SmsDataSource.insertTask(phoneNumber, message, taskType, appType)
            //发送到smsactor
            taskType match {
                case 1 => smsActor ! UTCMsg(fid, phoneNumber, message)
                case 2 => smsActor ! NEXMOMsg(fid, phoneNumber, message)
                case _ => {
                    logger.error("taskType error!")
                }
            }
        }
    }
}

object MysqlActor {
    def props = Props[MysqlActor]
}