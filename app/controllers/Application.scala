package controllers


import _root_.cache.redis.RedisCenter
import actor.MessageProtocol._
import actor.{ActorCenter, TraceLogActor, MyActor}
import akka.actor.Props
import akka.event.Logging
import play.api._
import play.api.db.DB
import play.api.mvc._
import play.libs.Akka
import slick.{AccessTokenDao, SlickDataSource}
import trace.RequestTrace
import akka.pattern.{ ask, pipe }
import play.api.libs.concurrent.Execution.Implicits._

import scala.concurrent.Await
import scala.concurrent.duration._
import akka.util.Timeout

import scala.util.{Success, Failure}

object Application extends Controller {

    def index = Action {
        Ok(views.html.index("Your new application is ready."))
    }

    def slickTest = Action {
        request => {
            val debug = request.getQueryString("debug").getOrElse("0").toInt
            val trace = RequestTrace()
            val num = AccessTokenDao().countTotalNum
            if (debug == 1)
                trace.addTrace("now test query slick db", System.currentTimeMillis())
            val log = trace.getAllLogInfo

            //use play datasource query
            if (debug == 1)
                Ok(s"total num is ${num}, log is ${log}")
            else
                Ok(s"total num is ${num}")
        }
    }

    def redisTest(key: String) = Action {
        request => {
            Logger.debug("key = " + key)
            val result = RedisCenter.getString(key)
            Logger.debug("result = " + result)
            Ok(s"ok,result is ${result}")
        }
    }

    def actorTrace(num: Int) = Action{
        request => {
            val debug = request.getQueryString("debug").getOrElse("0").toInt
            if (debug == 1) {
                ActorCenter.traceActor ! TraceLogInfo("=======x======")
            }
            implicit val timeout = Timeout(10 seconds)
            val result = ActorCenter.myActor ? num
            val textstr = Await.result(result, 10 second).asInstanceOf[String]

            if (debug == 1) {
                val tracelog = ActorCenter.traceActor ? GetTraceLogInfo()
                val log1 = Await.result(tracelog, 1 second).asInstanceOf[String]
                Ok(s"total result is ${textstr}, log is ${log1}")
            }
            else
                Ok(s"total result is ${textstr}")
        }
    }

    def actorTraceExtend = Action{
        request => {
            val debug = request.getQueryString("debug").getOrElse("0").toInt
            if (debug == 1) {
                ActorCenter.traceActor ! TraceLogInfoUUID("x","*new method test log trace*")
            }
            implicit val timeout = Timeout(1 seconds)
            val result = ActorCenter.myActor ? TraceNum("x", 1)
            val textstr = Await.result(result, 1 second).asInstanceOf[String]

            if (debug == 1) {
                val tracelog = ActorCenter.traceActor ? GetTraceLogInfoUUID("x")
                val log1 = Await.result(tracelog, 1 second).asInstanceOf[String]
                Ok(s"total result is ${textstr}, log is ${log1}")
            }
            else
                Ok(s"total result is ${textstr}")
        }
    }

    def redirectTest = Action{
        request => {
            implicit val timeout = Timeout(1 seconds)
            val result = ActorCenter.myActor ? "redirect"
            val textstr = Await.result(result, 1 second).asInstanceOf[String]

            Ok("textstr:"+textstr)
        }
    }
}