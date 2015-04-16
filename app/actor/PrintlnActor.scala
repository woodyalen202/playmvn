package actor

import akka.actor.Actor
import akka.actor.Actor.Receive
import akka.event.Logging

/**
 * Created by lucien on 15/4/16.
 */
class PrintlnActor extends Actor {

    val logger = Logging(context.system, this)
    
    override def receive: Receive = {
        case "over" => {
            sender ! "over"
        }
        case msg ⇒ {
            logger.debug("Received message '%s' in actor %s".format(msg, self.path.name))
        }
    }
}
