package burli

import com.microsoft.playwright.{Locator, Page}

abstract class OBJ {
  protected def isRoot: Boolean

  def pg: Page

  def myType: String = this.getClass.getSimpleName

  protected def pre = {
    val cla = this.getClass
    val n = cla.getName
    val t = cla.getTypeName
    s"$n $t"
  }

  def log(msg: Any) =
    println(pre + "." + msg)
}
