package burli

import com.microsoft.playwright.{Locator, Page}
import com.microsoft.playwright.options.AriaRole

case class LST[F <: FRM](b: By = Loc.Default)(using ref: Own[F])
  extends ATOM[F](b) {
  override def loc(pg: Page): Locator = {
    by match {
      case Loc.Default =>
        val opt = Page.GetByRoleOptions()
          .setName(myName)
          .setExact(false)
        pg.getByRole(AriaRole.LISTBOX, opt)

      case f: Function1[Page, Locator] =>
        f(pg)
    }
  }
}
