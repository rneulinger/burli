package burli

import scala.collection.JavaConverters.asScalaBufferConverter
import com.microsoft.playwright.*

abstract class PwRoot(val baseUrl:String) extends CanOwn{
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

  override def adopt(obj: OBJ): Unit =
    obj match {
      case atom: ATOM[_] =>
        adoptedAtoms = adoptedAtoms.appended(atom)

      case frm: FRM =>
        adoptedFrms = adoptedFrms.appended(frm)
    }

}
