package actor

import actor.MessageProtocol.{GetTraceLogInfoUUID, TraceLogInfoUUID, GetTraceLogInfo, TraceLogInfo}
import akka.actor.Actor
import akka.actor.Actor.Receive
import akka.event.Logging
import util.StringUtil

import scala.collection.mutable

//import scala.collection.immutable.HashMap

/**
 * Created by lucien on 15/4/15.
 */
class TraceLogActor extends Actor{

    val logger = Logging(context.system, this)

    val localLog = new ThreadLocal[String]

    val logMap = new mutable.HashMap[String, String]

    override def receive: Receive = {
        case TraceLogInfo(logInfo) => {
            val str = if (localLog.get() == null) logInfo else localLog.get() + "\\n" + logInfo
            localLog.set(str)
            logger.debug("addlog = " + logInfo)
        }
        case GetTraceLogInfo() => {
            val data = if (localLog.get() == null) "" else localLog.get()
            logger.debug(" getlog " + data)
            localLog.remove()
            sender() ! data
        }
        case TraceLogInfoUUID(uuid, logInfo) => {
            if (StringUtil.isNotBlank(logInfo)) {
                val str = if (logMap.contains(uuid)) {
                    logMap(uuid) + " || " + logInfo
                } else logInfo
                logMap(uuid)=str
                logger.debug(s"log store in map,now add log: ${logInfo}" )
            }
        }
        case GetTraceLogInfoUUID(uuid) => {
            val str = if (StringUtil.isBlank(logMap(uuid))) "" else logMap(uuid)
            logMap -= uuid
            sender() ! str
        }
    }
}
