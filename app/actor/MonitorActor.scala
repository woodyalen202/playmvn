package actor

import akka.actor.{Props, Terminated, Actor}
import akka.actor.Actor.Receive
import akka.event.Logging

/**
 * Created by lucien on 15/4/15.
 * 监控actor
 */
class MonitorActor extends Actor{

    val logger = Logging(context.system, this)


    var lastSender = context.system.deadLetters

    override def receive: Receive = {

        case Terminated(terminatedActorRef) => {
            logger.debug("terminated")
            logger.error(s"Child Actor {$terminatedActorRef} Terminated")
            lastSender ! "finished"
        }
        case _ => {
            logger.debug("now monitor...")
            println("logger = monitor")
        }
    }
}
