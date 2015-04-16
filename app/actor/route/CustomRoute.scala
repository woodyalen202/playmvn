package actor.route

import akka.actor.{ActorRef, Props, SupervisorStrategy, ActorSystem}
import akka.dispatch.Dispatchers
import akka.routing.{Router, RouterConfig}

/**
 * Created by lucien on 15/4/16.
 */
//class CustomRoute extends RouterConfig{
//
//
//    override def createRouter(system: ActorSystem): Router = {
//        val democratActor = system.actorOf(Props(new DemocratActor()), "d")
//        val republicanActor = system.actorOf(Props(new RepublicanActor()), "r")
//        val routees = Vector[ActorRef](democratActor, republicanActor)
//
//        system.registerRoutees(routees)
//
//        {
//            case (sender, message) ⇒
//                message match {
//                    case DemocratVote | DemocratCountResult ⇒
//                        List(Destination(sender, democratActor))
//                    case RepublicanVote | RepublicanCountResult ⇒
//                        List(Destination(sender, republicanActor))
//                }
//        }
//    }
//
//    def routerDispatcher: String = Dispatchers.DefaultDispatcherId
//    def supervisorStrategy: SupervisorStrategy = SupervisorStrategy.defaultStrategy
//
//}
