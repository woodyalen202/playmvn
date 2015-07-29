package slick

/**
 * Created by lucien on 15/5/22.
 */
object SlickCodeGenerate {

    def main(args: Array[String]) {
        scala.slick.codegen.SourceCodeGenerator.main(
            //        scala.slick.model.codegen.SourceCodeGenerator.main(
            Array[String]("scala.slick.driver.MySQLDriver",
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/sms_data",//
                "/Users/lucien/workspace/github/playmvn/app/",//path
                "slick.model",//package name
                "root", "")//username,password
        )
    }
}
