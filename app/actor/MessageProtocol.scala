package actor

import akka.actor.ActorRef

/**
 * Created by lucien on 15/4/15.
 */
object MessageProtocol {

    case class QuoteRequest()
    case class QuoteResponse(quoteString:String)

    //threadlocal log
    case class TraceLogInfo(logInfo: String)
    case class GetTraceLogInfo()
    //map log
    case class TraceLogInfoUUID(uuid: String, logInfo: String)
    case class GetTraceLogInfoUUID(uuid:String)

    case class RedirectMsg(source: ActorRef)


    //trace log uuid
    case class TraceNum(uuid:String, num: Int)
}
