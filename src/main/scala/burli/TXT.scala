package burli

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

case class TXT[F <: FRM](by:By=Loc.Default)(using ref:Own[F])
  extends ATOM[F](by)  {

  override def loc(pg: Page): Locator = {
    by match {
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(myName)
          .setExact(false)
        pg.getByRole(AriaRole.TEXTBOX, opt)

      case f:Function1[Page,Locator] =>
        f(pg)
    }
  }

  def fill( s:Any):F ={
    loc.fill(s.toString)
    own
  }
  def rightClick: F =
    log("rightClick")
    own

  def doubleClick: F =
    log("doubleClick")
    own

  def click: F =
    loc.click()
    own

  def click(cnt: Integer = 1): F =
    log("n-Click")
    own

  def isEmpty :F = ???
    own

  def nonEmpty:F = ???
    own

  def maxLength( n : Integer ):F = ???
    own

  def contains(snip:Any):F = ???
    own

  def matches( regex:String):F = ???
    own

  // asignment
  def let(txt: Any=""): F = {
    fill(txt)
    own
  }


  def <<(txt: Any=""): F = {
    let(txt)
    own
  }

  /**
   * result to variable
   * @return
   */
  def >> : F =
    own

  /**
   *
   * @return
   */
  def >>: : F =
    own

  // equality
  def :==(txt: Any=""): F =
    own

  def :!=(txt: Any=""): F =
    own

  // match
  def :=~(txt: Any=""): F =
    own

  def :!~(txt: Any): F =
    own

  // rel ops textual
  def :<=(txt: Any): F =
    own

  def :<(txt: Any): F =
    own

  def :>=(txt: Any): F =
    own

  def :>(txt: Any): F =
    own

  // rel ops numerical
  def #<(txt: Any): F =
    own

  def #<=(txt: Any): F =
    own

  def #>=(txt: Any): F =
    own

  def #>(txt: Any): F =
    own
}
