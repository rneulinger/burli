package burli

import com.microsoft.playwright.*

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
  case Id(id:String)
  case Exact
  case Contains
  case Default
}

type By = Loc | Function1[Page,Locator]

/** wrapper for given / using i FRM */
case class Own[+FRM](own:FRM)


//class X1 extends FRM
//  val y = Own[X1](this)
  //val x = Own[X1]

  //val x = LBL("")()



