package util

/**
 * Created by lucien on 15/4/16.
 */
object StringUtil {

    //匹配数字的正则表达式
    private lazy val HEXREGEX = "[\\-]?[0x|0X]?[\\d|a|b|c|d|e|f|A|B|C|D|E|F]+?".r
    private lazy val NUMBERREGEX = "".r

    def isBlank(str: String): Boolean = {
        str == null || str.isEmpty || str.trim.eq("")
    }

    def isNotBlank(str: String): Boolean = !isBlank(str)

    /**
     * 是否数字     参照numberutils.isNumber
     * @param str
     * @return
     */
    def isNumberic(str: String): Boolean = {

        //判断是否为空
        val result = if (isNotBlank(str)) {
            val chars = str.toCharArray
            //是否以－开头
            //val start = if (chars(0) == 45) 1 else 0
            val nonChars = if (chars(0) == 45) chars.tail else chars
            //二进制   01              (不需要判断)
            //八进制   0开头    0-7    (不需要判断,scala中后续不在支持)
            //十进制
                //整形、double、float、long

            //十六进制

            true
        } else false
        //非空判断字节码
        result
    }

    def isHexNumber(value: String): Boolean = {
        val index = if (value.startsWith("-")) 1 else 0
        value.startsWith("0x", index) || value.startsWith("0X", index) || value.startsWith("#", index);
    }


    def main(args: Array[String]) {
        val d1 = 12d
//        val d2 = 0001111
        val d3 = 0x342AF
        val d4 = -12
        val d5 = 12.21
        val d6 = .123F          // "[\\d]*?[\\.]?[e]?[\\d]+?[d|D|f|F]?$"
        val d7 = 12e12d         //

        println("d1.getClass = " + d1.getClass)
//        println("d2.getClass = " + d2.getClass)
        println("d3.getClass = " + d3.getClass)
        println("d3.toString = " + d3.toString)
        println("d4.getClass = " + d4.getClass)
        println("d5.getClass = " + d5.getClass)
        println("d6.getClass = " + d6.getClass)
        println("d7.getClass = " + d7.getClass)
        println("d7.toString = " + d7.toString)

        val //regex = "^[\\-]?[\\d]*?[\\.]?[\\d]*?[e|E]?[\\d]+?[d|D|f|F]?$".r
            regex = "^[\\-]?[\\d]*?[\\.]?[\\d]+?[e\\d|E\\d|\\d]*?[d|D|f|F]?$".r
        val d1str = regex.findFirstIn(d1.toString)
        println("d1str = " + d1str.getOrElse("none"))
        val d3str = regex.findFirstIn(d3.toString)
        println("d3str = " + d3str.getOrElse("none"))
        val d4str = regex.findFirstIn(d4.toString)
        println("d4str = " + d4str.getOrElse("none"))
        val d5str = regex.findFirstIn(d5.toString)
        println("d5str = " + d5str.getOrElse("none"))
        val d6str = regex.findFirstIn(d6.toString)
        println("d6str = " + d6str.getOrElse("none"))
        val d7str = regex.findFirstIn(d7.toString)
        println("d7str = " + d7str.getOrElse("none"))

        val str1 = "122e1"
        //如果存在0x,可以有\\d abcdef ABCDEF       [\\-]?[0x|0X]?[\\d|a|b|c|d|e|f|A|B|C|D|E|F]+?
        //如果不存在0x,可以有一个.,一个e，但是.e不能挨着,可以有dDfF
        //          [\\-]?[\\d]*?[\\.]?[\\d]+?[e\\d|E\\d|\\d]*?[d|D|f|F]?
        val d8str = regex.findFirstIn(str1)
        println("d8str = " + d8str.getOrElse("none"))
    }
}
