package sms

import akka.actor.{Props, Actor}
import akka.actor.Actor.Receive
import akka.event.Logging
import sms.SmsDataCenter._

/**
 * Created by lucien on 15/5/29.
 */
class FilterActor extends Actor{

    private val logger = Logging(context.system, this)

    val mysqlActor = context.actorOf(MysqlActor.props, "mysqlActor")

    import FilterActor._

    override def receive: Receive = {
        case Msg(phone, msg, appType) => {
            //对phone进行处理
            logger.info("[FilterAcotr]phone:{},msg:${},type:{}.now filtering",
                phone, msg, appType)
            //1:a-->+
            val phoneNumber = phone.replaceAll("a", "+");
            //2:空号过滤//redis中永久缓存
            //todo
            val nullOption = Option(phoneNumber)
            nullOption match {
                case None => {
                    logger.error("phone number is null!")
                    sender() ! ResultMsg(false, -1, ERROR_PHONE_NULLNUMBER)
                }
                case Some(_) => {
                    //3:黑名单过滤
                    //todo
                    val blackOption = filterBlackList(phoneNumber)
                    blackOption match {
                        case None => {
                            logger.error("phone number is in black list!")
                            sender() ! ResultMsg(false, -1, ERROR_PHONE_BLACK)
                        }
                        case Some(_) => {
                            //国内以＋86开头使用企信通发送,type=1,国外使用NEXMO发送
                            val taskType = if (phoneNumber.startsWith("+86")) 1 else 2
                            val message = msg.replace("+", " ")//内容中＋修改为空格
                            val atype = appType match {
                                    case "wrm" => 1
                                    case "wzp" => 2
                                    case _ => -1
                                }
                            //发送到mySqlActor
                            mysqlActor ! TaskMsg(phone, message, atype, taskType)
                        }
                    }
                }
            }
        }
        case _ => {
            logger.info("nothing to do!")
        }
    }
}

object FilterActor {
    def props = Props[FilterActor]

    //过滤电话号码
    def filterBlackList(phoneNumber: String): Option[Boolean] = {
        phoneNumber match {
            case "+4915750536417" => None
            case "+4915730544958" => None
            case "+4915750579084" => None
            case "+49015750555697" => None
            case "+4915750555697" => None
            case "+4915750573537" => None
            case _ => Some(true)
        }
    }
}
