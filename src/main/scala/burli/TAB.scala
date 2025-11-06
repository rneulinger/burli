package burli

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

case class TAB[F <: FRM](by:By=Loc.Default)(using ref:Own[F])
  extends ATOM[F](by)
{
  override def loc(pg: Page): Locator = {
    by match {
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(myName)
          .setExact(false)
        pg.getByRole(AriaRole.TAB, opt)

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }

  def click: FRM =
    loc(pg).click()
    own

}
