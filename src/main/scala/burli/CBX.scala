package burli

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

case class CBX[F <: FRM](b: By = Loc.Default)(using ref: Own[F])
  extends DATA[F](b) {

  override def loc(pg: Page): Locator = {
    by match {
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(fullName)
          .setExact(false)
        pg.getByRole(AriaRole.COMBOBOX, opt)

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
