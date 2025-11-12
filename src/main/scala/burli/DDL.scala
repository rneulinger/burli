package burli

import com.microsoft.playwright.{Locator, Page}

case class DDL[F <: FRM](by:By=Loc.Default)(using ref:Own[F])
  extends ATOM[F](by) {

  override def loc(pg: Page): Locator = ???
}
