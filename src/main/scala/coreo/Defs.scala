package coreo

import com.microsoft.playwright.*
import scala.jdk.CollectionConverters.*

//def gotoPath( path: String = "/"): Unit = {
//  import java.awt.Desktop
//  import java.net.URI
//
//  object BrowserLauncher {
//    def main(url:String): Unit = {
//      println("main:"+url)
//      if (Desktop.isDesktopSupported && Desktop.getDesktop.isSupported(Desktop.Action.BROWSE)) {
//        Desktop.getDesktop.browse(new URI(url))
//      } else {
//        println("Desktop browsing is not supported on this system.")
//      }
//    }
//  }
//  println(BaseUrl)
//  BrowserLauncher.main(BaseUrl + path)
//}

enum Loc {
  //case Id(id:String)
  case Exact
  case Contains
  case Default
}

type By = Loc | String | Function1[Page, Locator]

/** wrapper for given / using i FRM */
case class Own[+FRM](own: FRM)

object Defs {
  /**
   * splits up a string and make it camel case
   * "First name" -> "FirstName"
   *
   * @param s to convert
   * @return
   */

  def mkCamelCase(s: String): String = {
    val clean = s.trim.replace("-", " ") // a few more to come
    val chunks = clean.split("\\s+").toList
    val res = for (s <- chunks) yield
      val first = s.take(1).toUpperCase()
      val rest = s.drop(1)
      first + rest

    res.mkString("")
  }

  def toClipboard(text: String): String = {
    import java.awt.Toolkit
    import java.awt.datatransfer.StringSelection
    val selection = new StringSelection(text)
    val clipboard = Toolkit.getDefaultToolkit.getSystemClipboard
    clipboard.setContents(selection, null)
    //println(s"Copied to clipboard: $text")
    text
  }

  def gen(inp: String, frm:String = "") = {
    val tr = frm.trim
    val name = if tr.isEmpty then "New frame" else tr
    val cc = mkCamelCase(name)
    val p2 = if( cc == name) then "" else s", \"${name}\""

    val myFrm = if cc.endsWith("_") then cc else cc + "_"

    import java.io.{StringWriter, PrintWriter}

    val sw = new StringWriter()
    val pw = new PrintWriter(sw)

    val fields = inp.lines.iterator.asScala.
      mkString(",").split(",").toList.map(_.trim).filter(_.nonEmpty)

    if fields.size != fields.toSet.size then
      throw IllegalArgumentException("Duplicate(s) in field(s) definitions")

//    def mkCamelCase(s: String) = {
//      val clean = s.trim.replace("-", " ") // a few more to come
//      val chunks = clean.split("\\s+").toList
//      val res = for (s <- chunks) yield
//        val first = s.take(1).toUpperCase()
//        val rest = s.drop(1)
//        first + rest
//
//      res.mkString("")
//    }

    def declFields() = {
      val Buttons = Set("Add", "Edit", "Delete", "Next", "Finish", "Cancel", "Back")

      def getType( s:String) = if Buttons.contains(s) then "BTN()" else "TXT()"

      // TODO there can be still a duplicate conflict in aliases
      for (fl <- fields.filter(_.nonEmpty)) {
        val f = (fl, mkCamelCase(fl))
        val typ = getType(f._1)
        if (f._1 == f._2) { // mo alias
          pw.println(s"  val ${f._1} = $typ")
        } else {
          pw.println(s"  val `${f._1}` = $typ")
          pw.println(s"  def ${f._2} = `${f._1}` // alias")
        }
      }
    }

    pw.println(
      s"""import burli.*
        |import burli.bricks.*
        |import com.microsoft.playwright.*
        |import com.microsoft.playwright.options.*
        |
        |final class $myFrm ( own:CanOwn ) extends FRM(own${p2}){
        |  // tag::fields[]
        |  given ref: Own[$myFrm] = Own(this)
        |  // TODO set path if you can NAVIGATE directly to this page;  otherwise delete this
        |  override def path: String = ""
        |""".stripMargin)

    declFields()

    pw.println(
      s"""
        |  // end::fields[]
        |}
        |
        |
        |val _$myFrm = $myFrm(this)
        |""".stripMargin)
    pw.flush()
    sw.toString
  }

  /**
   *
   * @param s1
   * @param s2
   * @return
   * println(levenshtein("kitten", "sitting")) // Output: 3
   * println(levenshtein("scala", "scala")) // Output: 0
   * println(levenshtein("flaw", "lawn")) // Output: 2
   *
   *
   */
  def levenshtein(s1: String, s2: String): Int = {
    val len1 = s1.length
    val len2 = s2.length
    val dim = len1.max(len2) + 1
    val dp: Array[Array[Int]] = Array.ofDim(dim, dim)

    for (i <- 0 to len1) dp(i)(0) = i
    for (j <- 0 to len2) dp(0)(j) = j

    for (i <- 1 to len1) {
      for (j <- 1 to len2) {
        val cost = if (s1(i - 1) == s2(j - 1)) 0 else 1
        dp(i)(j) = List(
          dp(i - 1)(j) + 1, // deletion
          dp(i)(j - 1) + 1, // insertion
          dp(i - 1)(j - 1) + cost // substitution
        ).min
      }
    }
    dp(len1)(len2)
  }

}

def gen( s:String ):Unit = Defs.toClipboard(Defs.gen(s))


