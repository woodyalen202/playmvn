package controllers

import actor.ActorCenter
import actor.MessageProtocol._
import akka.actor.Props
import akka.pattern.ask
import akka.util.Timeout
import play.api._
import play.api.libs.concurrent.Akka
import play.api.mvc._
import play.libs.Json
import slick.AccessTokenDao
import sms.SmsDataCenter.Msg
import sms.{FilterActor}
import trace.RequestTrace
import play.api.Play.current
import scala.concurrent.Await
import scala.concurrent.duration._

/**
 * 短信发送控制器
 * 目标：高效、可用、容灾
 *
 * 接收请求后
 * 过滤模块，主要是检查手机号码，是否空号、黑名单中
 * 检查通过后，添加到任务列表
 * 发送短信
 * 结果返回，同时更新任务状态
 *
 */
object SmsController extends Controller {

    val filterActor = Akka.system.actorOf(FilterActor.props, "filterActor")

    def index = Action {
        Ok(views.html.index("Your new application is ready."))
    }

    //短信发送请求    http参数
    def singleSmsSender = Action {
        request => {
            //请求过来的是json格式
            val appType = request.getQueryString("app_type").get
            val phone = request.getQueryString("phone").get;
            val msg = request.getQueryString("message").get
            Logger.info(s"appType:${appType},phone:${phone},msg:${msg}")
            //将请求转到filter进行处理
            implicit val timeout = Timeout(2 seconds)
            val result = filterActor ? Msg(phone, msg, appType)
            val textstr = Await.result(result, 5 second).asInstanceOf[String]
            Ok(s"result:${textstr}")
        }
    }

}