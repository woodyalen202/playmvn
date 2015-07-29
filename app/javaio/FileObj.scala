package javaio

import scala.io.Source

/**
 * Created by lucien on 15/5/26.
 */
object FileObj {

    def main(args: Array[String]) {
        val path = "/Users/lucien/.gitconfig";
        val source = Source.fromFile(path)

        val lineIter = source.getLines()

        for (str <- lineIter) {
            println(str)
        }

        source.close()
    }

}
