package carConf

import burli.*

/*
{
  import $ivy.`com.microsoft.playwright:playwright:1.53.0`
  import $cp.`./build/libs/burli.jar`
  import com.microsoft.playwright.*
  import com.microsoft.playwright.options.*
  import burli.*
  import carConf.*
}
*/
val s = "file:///C:/burli/carconfigWeb/html/CarConfig.htm?lang=en#"
class Main() extends PwRoot(s) {

  // TODO Mene
  val CarConfig = CarConfig_(this)
  val Specials = Specials_(this)
  val Vehicles = Vehicles_(this)

}

lazy val C1 = new Main()
lazy val C2 = new Main()
lazy val `/` = C1
