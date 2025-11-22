package carConf

import coreo.*

import com.microsoft.playwright.options.AriaRole
import com.microsoft.playwright.{Locator, Page}

import scala.language.postfixOps

class CarConfig_(own: CanOwn)
  extends FRM(own) {

  // tag::fields[]

  given ref: Own[CarConfig_] = Own(this)

  def byName( name:String ) : Page => Locator =
    _.getByRole(AriaRole.LINK,
      new Page.GetByRoleOptions().setName(name))

  val VehiclesTab = TAB(byName("Vehicles"))
  val SpecialsTab = TAB(byName("Specials"))
  val AccessoriesTab = TAB(byName("Accessories"))

  // end::fields[]
}
