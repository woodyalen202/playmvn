package trace

/**
 * Created by lucien on 15/4/13.
 * 跟踪请求访问的内容，记录必要的信息和处理时间
 * 使用threadlocal存储
 */
class RequestTrace {

//    def this(x: Long) {
//        this()
//        initThreadLocal
//    }
    //存储请求的日志信息
    private val local: ThreadLocal[StringBuilder] = new ThreadLocal[StringBuilder]

    //存储请求的时间,用于计算方法处理时间
    private val startTimeLocal: ThreadLocal[Long] = new ThreadLocal[Long]

    private def initThreadLocal: Unit = {
        local.set(StringBuilder.newBuilder)
        startTimeLocal.set(System.currentTimeMillis())//默认初始化时间
    }

    //重置开始时间
    def resetStartTime(startTime: Long) {
        startTimeLocal.set(startTime)
    }

    //计算执行程序花费了多长时间
    def costTime(endTime: Long): Long = {
        endTime - startTimeLocal.get
    }

    //记录访问日志
    def addTrace(tracelog: String): Unit = {
        local.get().append(System.getProperty("line.separator")).append(tracelog)
    }

    def addTrace(tracelog: String, endTime: Long): Unit = {
        local.get().append(System.getProperty("line.separator")).append(tracelog)
        val log = s"cost time is ${costTime(endTime)}"
        local.get().append(System.getProperty("line.separator")).append(log)
    }

    //获取所有的日志信息
    def getAllLogInfo: String = {
        local.get().toString()
    }
}
object RequestTrace {
    def apply() = {
        val trace = new RequestTrace()
        trace.initThreadLocal
        trace
    }
}
