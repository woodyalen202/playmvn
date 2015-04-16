package actor

import akka.actor.{ActorSystem, Props}

/**
 * Created by lucien on 15/4/15.
 */
object TestKitDemo
{


    def main(args: Array[String]) {
        val actorSystem = ActorSystem("testkit")
        val myActor = actorSystem.actorOf(Props[MyActor], "myactor")
        val monitorActor = actorSystem.actorOf(Props[MonitorActor], "monitor")


        myActor ! "test"
        myActor ! "akdsjfkadjfkas;d"
        myActor ! 99999999


        actorSystem.awaitTermination();
    }
}
