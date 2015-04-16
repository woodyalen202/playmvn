package actor

import actor.MessageProtocol._
import akka.actor.{Props, ActorLogging, Actor}
import akka.actor.Actor.Receive
import akka.event.Logging
import akka.routing._
import play.libs.Akka

import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import akka.util.Timeout
import akka.pattern.{ ask, pipe }

import scala.util.Random

/**
 * Created by lucien on 15/4/15.
 */
class MyActor extends Actor with ActorLogging{

    val logger = Logging(context.system, this)

    val quotes = List(
    "Moderation is for cowards",
    "Anything worth doing is worth overdoing",
    "The trouble is you think you have time",
    "You never gonna know if you never even try")


    override def receive: Receive = {
        case QuoteRequest => {
            val quoteResponse = QuoteResponse(quotes(Random.nextInt(quotes.size)))
            logger.debug("quotes = " + quoteResponse.quoteString)
            log.info(quoteResponse.quoteString)
        }
        case "test" => {
            logger.debug("this is debug")
            logger.debug("test = ")
        }
        case "redirect" => {
            logger.debug(" now turn to redirect msg! ")
            self ! RedirectMsg(sender())
        }
        case num: Int => {
            logger.debug("num = " + num)
            val actorNum = if (num / 10 <= 0) 1 else (num-1) / 10 + 1
//          implicit val timeout = Timeout(1 seconds)
            (0 to num).par map {
                i =>
                    ActorCenter.smallestMailboxRouter ! i
            }
            //需要判断route中已经执行全部完成了,执行是否成功不重要
            val count = num % quotes.size
            sender() ! quotes(count)
        }
        case RedirectMsg(source) => {
            source ! "what happend in futue?"
        }
        case TraceNum(uuid, num) => {
            ActorCenter.traceActor ! TraceLogInfoUUID(uuid, "now deal with tracenum")
            sender() ! quotes(Random.nextInt(quotes.size))
        }
        case _ =>
            logger.debug(s"now is lkjlkn,,,,")
    }
}
