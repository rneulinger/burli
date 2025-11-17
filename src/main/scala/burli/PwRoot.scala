package burli

import scala.jdk.CollectionConverters.*
import com.microsoft.playwright.*

class PwRoot(val baseUrl: String) extends CanOwn {
  def nameOfApp = "No Name"
  def predefBaseUrls = Map[String, String]()

  private var adoptedAtoms = List[ATOM[?]]()
  lazy val atoms: Map[String, ATOM[?]] = adoptedAtoms.map(a => a.fullName -> a).toMap

  private var adoptedFrms = List[FRM]()
  lazy val frms: Map[String, FRM] = adoptedFrms.map(a => a.myType -> a).toMap


  final def adopt(obj: OBJ): Unit =
    obj match {
      case atom: ATOM[_] =>
        adoptedAtoms = adoptedAtoms.appended(atom)

      case frm: FRM =>
        adoptedFrms = adoptedFrms.appended(frm)
    }

  lazy val playwright: Playwright = Playwright.create()

  lazy val bOpts = new BrowserType.LaunchOptions()
    .setHeadless(false)
  lazy val browser: Browser = playwright
    .chromium()
    .launch(bOpts)

  lazy val cOpts: Browser.NewContextOptions = new Browser.NewContextOptions()
    .setIgnoreHTTPSErrors(true)
  lazy val context: BrowserContext = browser.newContext(cOpts) // HTTPS-Fehler ignorieren


  lazy val pg: Page = {
    val tmp = context.newPage()
    tmp.navigate(baseUrl)
    tmp
  }

  // Cookies auslesen
  private lazy val cookie = context.cookies().asScala

  //  cookies.foreach(cookie => println(s"${cookie.name()} = ${cookie.value()}"))
  def close(): Unit = {
    browser.close()
    playwright.close()
  }

  def home: Response = pg.navigate(baseUrl)


  /**
   * current view
   */

  var currentFrm:FRM  = new FRM(this){}
  val defaultFrm = currentFrm
  /**
   * visit: push current view on stack, arg becomes current,
   * return: push curren tin history, pop and set current
   */
  val viewStack = scala.collection.mutable.Stack[FRM]()
  val viewHistory = scala.collection.mutable.Stack[FRM]()

  def onto( frm:FRM):Unit = {
    viewStack.push(currentFrm)
    currentFrm = frm
    println( "changed to frm:"+ frm.myType)
    println( frm.dump())
  }

  def goto(name: String): Unit = {
    val dest = Defs.mkCamelCase(name) + "_"
    if (frms.keySet.contains(dest)) {
      frms(dest).goto()
    } else{
      println( "goto: cannot find frame:" + dest )
    }
  }

  def onto( name:String): Unit = {
    val dest = Defs.mkCamelCase(name) + "_"
    if ( frms.keySet.contains(dest) ){
      onto( frms(dest))
    } else{
      println( "onto: cannot find frame:" + dest )
    }
  }

  def back:Unit = {
    if (viewStack.nonEmpty) {
      currentFrm = viewStack.pop()
    } else{
      currentFrm = defaultFrm
    }
  }

  final def dump(s: String = ""): Unit = {
    println(atoms)
    val hits = frms.filter(_._1.contains(s))
    for (frm <- hits) {
        println()
        frm._2.dump()
    }
  }

  override def openUrl(path: String): Unit = {
    pg.navigate(baseUrl + path)
  }

  def gui: GUI = {
    new GUI(this)
  }

  def gen: GenGUI = {
    new GenGUI("")
  }

}
