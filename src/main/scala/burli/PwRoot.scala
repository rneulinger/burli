package burli

import scala.collection.JavaConverters.asScalaBufferConverter
import com.microsoft.playwright.*

class PwRoot(val baseUrl:String) extends CanOwn{
  val playwright: Playwright = Playwright.create()

  val bOpts = new BrowserType.LaunchOptions()
    .setHeadless(false)
  val browser: Browser = playwright
    .chromium()
    .launch(bOpts)

  val cOpts = new Browser.NewContextOptions()
    .setIgnoreHTTPSErrors(true)
  val context = browser.newContext( cOpts)// HTTPS-Fehler ignorieren


  val pg: Page = context.newPage()
  pg.navigate(baseUrl)

  // Cookies auslesen
  private val cookie = context.cookies().asScala
  //  cookies.foreach(cookie => println(s"${cookie.name()} = ${cookie.value()}"))
  def close() :Unit= {
    browser.close()
    playwright.close()
  }

  def home:Response =  pg.navigate(baseUrl)

  private var adoptedAtoms = List[ATOM[?]]()
  lazy val atoms: Map[String, ATOM[?]] = {
    val tmp = adoptedAtoms.map(a => a.myName -> a).toMap
    tmp
  }
  private var adoptedFrms = List[FRM]()
  lazy val frms: Map[String, FRM] = {
    val tmp = adoptedFrms.map(a => a.myType -> a).toMap
    tmp
  }

  final def adopt(obj: OBJ): Unit =
    obj match {
      case atom: ATOM[_] =>
        adoptedAtoms = adoptedAtoms.appended(atom)

      case frm: FRM =>
        adoptedFrms = adoptedFrms.appended(frm)
    }
  final def dump(): Unit = {
    println( atoms )
    for(  frm <- frms ){
      println( frm._1)
      val len = frm._2.atoms.map(_._1.length).max
      for ( atom <- frm._2.atoms ){
        val name = atom._1
        println("\t" + name + " " * (len - name.length) + " : " + atom._2)
      }
    }
  }
}
