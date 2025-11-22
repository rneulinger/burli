package burli

import com.microsoft.playwright.{Locator, Page}

case class CAL[F <: FRM](b: By = Loc.Default)(using ref: Own[F])
  extends DATA[F](b) {

  override def loc(pg: Page): Locator = ???
}
