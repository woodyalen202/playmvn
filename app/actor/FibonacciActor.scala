package actor

import akka.actor.Actor

import scala.annotation.tailrec
import akka.pattern.AskSupport

/**
 * Created by lucien on 15/4/16.
 */
class FibonacciActor extends Actor  {

    def receive = {
        case nbr: Int => sender ! fibonacci(nbr)
    }

    private def fibonacci(n: Int): Int = {
        @tailrec
        def fib(n: Int, b: Int, a: Int): Int = n match {
            case 0 ⇒ a
            case _ ⇒ fib(n - 1, a + b, b)
        }

        fib(n, 1, 0)
    }
}
