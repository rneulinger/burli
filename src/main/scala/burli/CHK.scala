package burli

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

case class CHK[F <: FRM](b: By = Loc.Default)(using ref: Own[F])
  extends ATOM[F](b) {

  override def loc(pg: Page): Locator = {
    by match {
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(myName)
          .setExact(false)
        pg.getByRole(AriaRole.CHECKBOX, opt)

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }

  def check: F = {
    println(s"")
    own
  }

  def uncheck: F =
    own
}
