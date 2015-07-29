import org.specs2.mutable.Specification

/**
 * Created by lucien on 15/5/22.
 *
 */
class DemoSpec extends Specification{
//class DemoSpec extends PlaySpec{
    //单元测试规范派生于org.specs2.mutable.Specification ，使用should/in的格式。
    "HelloWorldUnit" should {
        "contain 11 characters" in {
            "Hello world" must have size(11)
        }
        "start with 'Hello'" in {
            "Hello world" must startWith("Hello")
        }
        "end with 'world'" in {
            "Hello world" must endWith("world")
        }
    }

}
