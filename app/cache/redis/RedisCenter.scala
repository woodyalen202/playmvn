package cache.redis

import com.redis.RedisClientPool
import com.typesafe.config.ConfigFactory
import play.{Application, Play}
import play.api.{Mode, Configuration}
import play.api.Play.current

/**
 * Created by lucien on 15/4/14.
 */
object RedisCenter {

    val redisConfig = {
        ConfigFactory.load("redis.conf")
    }
    val host = redisConfig.getString("redis.host")
    val port = redisConfig.getInt("redis.port")
    val clients = new RedisClientPool(host, port)


    // String
    def getString(key: String) = {
        clients.withClient {
            client => {
                client.get(key).getOrElse("")
            }
        }
    }

    // lpush
    def lp(msgs: List[String]) = {
        clients.withClient {
            client => {
                msgs.foreach(client.lpush("list-l", _))
                client.llen("list-l")
            }
        }
    }

    // rpush
    def rp(msgs: List[String]) = {
        clients.withClient {
            client => {
                msgs.foreach(client.rpush("list-r", _))
                client.llen("list-r")
            }
        }
    }

    // set
    def set(msgs: List[String]) = {
        clients.withClient {
            client => {
                var i = 0
                msgs.foreach { v =>
                    client.set("key-%d".format(i), v)
                    i += 1
                }
                Some(1000)
            }
        }
    }
}
