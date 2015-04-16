package util

/**
 * Created by lucien on 15/4/16.
 */
object StringUtil {

    def isBlank(str: String): Boolean = {
        str == null || str.isEmpty || str.trim.eq("")
    }

    def isNotBlank(str: String): Boolean = !isBlank(str)
}
