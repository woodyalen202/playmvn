package sms.model

/**
 * Created by lucien on 15/5/28.
 */
object SmsTables extends {
    val profile = scala.slick.driver.MySQLDriver
} with SmsTables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait SmsTables {
    val profile: scala.slick.driver.JdbcProfile
    import profile.simple._
    // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
    import scala.slick.jdbc.{GetResult => GR}

    /** DDL for all tables. Call .create to execute. */
    lazy val ddl = ReportUser.ddl ++ SmsData.ddl

    /** Entity class storing rows of table ReportUser
      *  @param fphoneNum Database column Fphone_num DBType(INT UNSIGNED), PrimaryKey
      *  @param fappType Database column Fapp_type DBType(INT UNSIGNED), Default(0) */
    case class ReportUserRow(fphoneNum: Int, fappType: Int = 0)
    /** GetResult implicit for fetching ReportUserRow objects using plain SQL queries */
    implicit def GetResultReportUserRow(implicit e0: GR[Int]): GR[ReportUserRow] = GR{
        prs => import prs._
            ReportUserRow.tupled((<<[Int], <<[Int]))
    }
    /** Table description of table report_user. Objects of this class serve as prototypes for rows in queries. */
    class ReportUser(_tableTag: Tag) extends Table[ReportUserRow](_tableTag, "report_user") {
        def * = (fphoneNum, fappType) <> (ReportUserRow.tupled, ReportUserRow.unapply)
        /** Maps whole row to an option. Useful for outer joins. */
        def ? = (fphoneNum.?, fappType.?).shaped.<>({r=>import r._; _1.map(_=> ReportUserRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

        /** Database column Fphone_num DBType(INT UNSIGNED), PrimaryKey */
        val fphoneNum: Column[Int] = column[Int]("Fphone_num", O.PrimaryKey)
        /** Database column Fapp_type DBType(INT UNSIGNED), Default(0) */
        val fappType: Column[Int] = column[Int]("Fapp_type", O.Default(0))
    }
    /** Collection-like TableQuery object for table ReportUser */
    lazy val ReportUser = new TableQuery(tag => new ReportUser(tag))

    /** Entity class storing rows of table SmsData
      *  @param fid Database column Fid DBType(INT), AutoInc, PrimaryKey
      *  @param fphone Database column Fphone DBType(VARCHAR), Length(20,true), Default()
      *  @param fmessage Database column Fmessage DBType(TEXT), Length(65535,true)
      *  @param finsertTime Database column Finsert_time DBType(INT UNSIGNED)
      *  @param fsendTime Database column Fsend_time DBType(INT UNSIGNED)
      *  @param fstatus Database column Fstatus DBType(TINYINT), Default(0)
      *  @param fsendBy Database column Fsend_by DBType(TINYINT), Default(0)
      *  @param fappType Database column Fapp_type DBType(INT UNSIGNED)
      *  @param fsendAfter Database column Fsend_after DBType(TINYINT), Default(0)
      *  @param fisSend Database column Fis_send DBType(TINYINT UNSIGNED), Default(0) */
    case class SmsDataRow(fid: Int, fphone: String = "", fmessage: String, finsertTime: Long, fsendTime: Long, fstatus: Int = 0, fsendBy: Int = 0, fappType: Int, fsendAfter: Int = 0, fisSend: Int = 0)
    /** GetResult implicit for fetching SmsDataRow objects using plain SQL queries */
    implicit def GetResultSmsDataRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Byte]): GR[SmsDataRow] = GR{
        prs => import prs._
            SmsDataRow.tupled((<<[Int], <<[String], <<[String], <<[Long], <<[Long], <<[Int], <<[Int], <<[Int], <<[Int], <<[Int]))
    }
    /** Table description of table sms_data. Objects of this class serve as prototypes for rows in queries. */
    class SmsData(_tableTag: Tag) extends Table[SmsDataRow](_tableTag, "sms_data") {
        def * = (fid, fphone, fmessage, finsertTime, fsendTime, fstatus, fsendBy, fappType, fsendAfter, fisSend) <> (SmsDataRow.tupled, SmsDataRow.unapply)
        /** Maps whole row to an option. Useful for outer joins. */
        def ? = (fid.?, fphone.?, fmessage.?, finsertTime.?, fsendTime.?, fstatus.?, fsendBy.?, fappType.?, fsendAfter.?, fisSend.?).shaped.<>({r=>import r._; _1.map(_=> SmsDataRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

        /** Database column Fid DBType(INT), AutoInc, PrimaryKey */
        val fid: Column[Int] = column[Int]("Fid", O.AutoInc, O.PrimaryKey)
        /** Database column Fphone DBType(VARCHAR), Length(20,true), Default() */
        val fphone: Column[String] = column[String]("Fphone", O.Length(20,varying=true), O.Default(""))
        /** Database column Fmessage DBType(TEXT), Length(65535,true) */
        val fmessage: Column[String] = column[String]("Fmessage", O.Length(65535,varying=true))
        /** Database column Finsert_time DBType(INT UNSIGNED) */
        val finsertTime: Column[Long] = column[Long]("Finsert_time")
        /** Database column Fsend_time DBType(INT UNSIGNED) */
        val fsendTime: Column[Long] = column[Long]("Fsend_time")
        /** Database column Fstatus DBType(TINYINT), Default(0) */
        val fstatus: Column[Int] = column[Int]("Fstatus", O.Default(0))
        /** Database column Fsend_by DBType(TINYINT), Default(0) */
        val fsendBy: Column[Int] = column[Int]("Fsend_by", O.Default(0))
        /** Database column Fapp_type DBType(INT UNSIGNED) */
        val fappType: Column[Int] = column[Int]("Fapp_type")
        /** Database column Fsend_after DBType(TINYINT), Default(0) */
        val fsendAfter: Column[Int] = column[Int]("Fsend_after", O.Default(0))
        /** Database column Fis_send DBType(TINYINT UNSIGNED), Default(0) */
        val fisSend: Column[Int] = column[Int]("Fis_send", O.Default(0))

        /** Index over (fstatus) (database name status) */
        val index1 = index("status", fstatus)
    }
    /** Collection-like TableQuery object for table SmsData */
    lazy val SmsData = new TableQuery(tag => new SmsData(tag))
}