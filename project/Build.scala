/**
 * Created by lucien on 15-3-31.
 */
class Build {

  def fromEnv(name: String) = System.getenv(name) match {
    case null => None
    case value => Some(value)
  }

  val appName = fromEnv("project.artifactId").getOrElse("woodyalen202")
  val appVersion = fromEnv("project.version").getOrElse("1.0.1-SNAPSHOT")
}
