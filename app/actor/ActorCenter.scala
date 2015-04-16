package actor

import akka.actor.Props
import akka.routing._
import akka.util
import akka.pattern.ask
import scala.concurrent.duration._
import akka.util.Timeout
import play.libs.Akka

import scala.concurrent.Await


/**
 * Created by lucien on 15/4/15.
 */
object ActorCenter {

    val myActor = Akka.system.actorOf(Props[MyActor], "myActor")

    val traceActor = Akka.system.actorOf(Props[TraceLogActor], "traceActor")

    val monitor = Akka.system.actorOf(Props[MonitorActor], "monitor")

    //请求发送到myactor，根据数量大小生成route运行，route中包含的actor数量由请求数量决定，
    //同时myactor监控route运行，运行结束后将一定量的actor存入actor池，其他actor关闭。
    //轮询调度可以最大程度保证每个actor平均处理,但是不能保证每个actor处理的时间平均

    //动态调整route中actor的数量
    private val resizer = DefaultResizer(lowerBound = 2, upperBound = 15)
//    val router3 = Akka.system.actorOf(Props[PrintlnActor].withRouter(
//        RoundRobinRouter(resizer = Some(resizer))))
    //轮询调度
//    val roundRobinRouter =
//        Akka.system.actorOf(Props[PrintlnActor].withRouter(RoundRobinRouter(5)), "router")
//    //随机路由
//    val randomRouter =
//        Akka.system.actorOf(Props[PrintlnActor].withRouter(RandomRouter(5)), "router")
    //未挂起邮箱中最少的优先
    val actorPool = SmallestMailboxPool(2).withResizer(resizer)
    val smallestMailboxRouter =
        Akka.system.actorOf(Props[PrintlnActor].withRouter(actorPool), "router")
    //转发给所有actor,用于广播
//    val broadcastRouter =
//        Akka.system.actorOf(Props[PrintlnActor].withRouter(BroadcastRouter(5)), "router")
//
//    //将消息作为future转发给所有actor,最快返回的将返回给sender
//    val scatterGatherFirstCompletedRouter = Akka.system.actorOf(
//        Props[FibonacciActor].withRouter(ScatterGatherFirstCompletedRouter(
//            nrOfInstances = 5, within = 2 seconds)), "router")
//
//    implicit val timeout = Timeout(1 seconds)
//    val futureResult = scatterGatherFirstCompletedRouter ? 10
//    val result = Await.result(futureResult, 1 second)


}
