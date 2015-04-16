package slick.model
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{GetResult => GR}
  
  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = AccessToken.ddl ++ TbRenmaiUser.ddl
  
  /** Entity class storing rows of table AccessToken
   *  @param fid Database column Fid DBType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param fuid Database column Fuid DBType(BIGINT)
   *  @param fwuid Database column Fwuid DBType(BIGINT)
   *  @param faccessToken Database column Faccess_token DBType(VARCHAR), Length(50,true), Default()
   *  @param ftime Database column Ftime DBType(INT)
   *  @param fexpireTime Database column Fexpire_time DBType(INT) */
  case class AccessTokenRow(fid: Int, fuid: Long, fwuid: Long, faccessToken: String = "", ftime: Int, fexpireTime: Int)
  /** GetResult implicit for fetching AccessTokenRow objects using plain SQL queries */
  implicit def GetResultAccessTokenRow(implicit e0: GR[Int], e1: GR[Long], e2: GR[String]): GR[AccessTokenRow] = GR{
    prs => import prs._
    AccessTokenRow.tupled((<<[Int], <<[Long], <<[Long], <<[String], <<[Int], <<[Int]))
  }
  /** Table description of table access_token. Objects of this class serve as prototypes for rows in queries. */
  class AccessToken(_tableTag: Tag) extends Table[AccessTokenRow](_tableTag, "access_token") {
    def * = (fid, fuid, fwuid, faccessToken, ftime, fexpireTime) <> (AccessTokenRow.tupled, AccessTokenRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (fid.?, fuid.?, fwuid.?, faccessToken.?, ftime.?, fexpireTime.?).shaped.<>({r=>import r._; _1.map(_=> AccessTokenRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column Fid DBType(INT UNSIGNED), AutoInc, PrimaryKey */
    val fid: Column[Int] = column[Int]("Fid", O.AutoInc, O.PrimaryKey)
    /** Database column Fuid DBType(BIGINT) */
    val fuid: Column[Long] = column[Long]("Fuid")
    /** Database column Fwuid DBType(BIGINT) */
    val fwuid: Column[Long] = column[Long]("Fwuid")
    /** Database column Faccess_token DBType(VARCHAR), Length(50,true), Default() */
    val faccessToken: Column[String] = column[String]("Faccess_token", O.Length(50,varying=true), O.Default(""))
    /** Database column Ftime DBType(INT) */
    val ftime: Column[Int] = column[Int]("Ftime")
    /** Database column Fexpire_time DBType(INT) */
    val fexpireTime: Column[Int] = column[Int]("Fexpire_time")
    
    /** Uniqueness Index over (fuid) (database name Fuid) */
    val index1 = index("Fuid", fuid, unique=true)
  }
  /** Collection-like TableQuery object for table AccessToken */
  lazy val AccessToken = new TableQuery(tag => new AccessToken(tag))
  
  /** Entity class storing rows of table TbRenmaiUser
   *  @param uid Database column uid DBType(BIGINT), PrimaryKey
   *  @param username Database column username DBType(VARCHAR), Length(20,true)
   *  @param email Database column email DBType(VARCHAR), Length(255,true)
   *  @param password Database column password DBType(VARCHAR), Length(100,true)
   *  @param createTime Database column create_time DBType(TIMESTAMP) */
  case class TbRenmaiUserRow(uid: Long, username: String, email: String, password: String, createTime: java.sql.Timestamp)
  /** GetResult implicit for fetching TbRenmaiUserRow objects using plain SQL queries */
  implicit def GetResultTbRenmaiUserRow(implicit e0: GR[Long], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[TbRenmaiUserRow] = GR{
    prs => import prs._
    TbRenmaiUserRow.tupled((<<[Long], <<[String], <<[String], <<[String], <<[java.sql.Timestamp]))
  }
  /** Table description of table tb_renmai_user. Objects of this class serve as prototypes for rows in queries. */
  class TbRenmaiUser(_tableTag: Tag) extends Table[TbRenmaiUserRow](_tableTag, "tb_renmai_user") {
    def * = (uid, username, email, password, createTime) <> (TbRenmaiUserRow.tupled, TbRenmaiUserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (uid.?, username.?, email.?, password.?, createTime.?).shaped.<>({r=>import r._; _1.map(_=> TbRenmaiUserRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column uid DBType(BIGINT), PrimaryKey */
    val uid: Column[Long] = column[Long]("uid", O.PrimaryKey)
    /** Database column username DBType(VARCHAR), Length(20,true) */
    val username: Column[String] = column[String]("username", O.Length(20,varying=true))
    /** Database column email DBType(VARCHAR), Length(255,true) */
    val email: Column[String] = column[String]("email", O.Length(255,varying=true))
    /** Database column password DBType(VARCHAR), Length(100,true) */
    val password: Column[String] = column[String]("password", O.Length(100,varying=true))
    /** Database column create_time DBType(TIMESTAMP) */
    val createTime: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("create_time")
    
    /** Uniqueness Index over (email) (database name IX_email) */
    val index1 = index("IX_email", email, unique=true)
  }
  /** Collection-like TableQuery object for table TbRenmaiUser */
  lazy val TbRenmaiUser = new TableQuery(tag => new TbRenmaiUser(tag))
}