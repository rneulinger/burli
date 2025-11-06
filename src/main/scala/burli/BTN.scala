package burli

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

case class BTN[F <: FRM](by:By=Loc.Default)(using ref:Own[F])
  extends ATOM[F](by) {

  override def loc(pg: Page): Locator = {
    by match {
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(myName)
          .setExact(false)
        pg.getByRole(AriaRole.BUTTON, opt)

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }

  def click: FRM =
    loc(pg).click()
    own

  def click(cnt: Integer = 1): FRM = {
    own
  }
}
