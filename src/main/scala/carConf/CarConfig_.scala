package carConf

import burli.*
import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

class CarConfig_  ( own:CanOwn ) 
  extends FRM(own){

  // tag::fields[]
  given ref: Own[CarConfig_] = Own(this)

  val Vehicles = TAB(pg => pg.
    getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Vehicles")))
  val Specials = TAB(pg => pg.
    getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Specials")))
  val Accessories = TAB(pg => pg.
    getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Accessories")))
  // end::fields[]
}
