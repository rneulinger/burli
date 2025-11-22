package carConf.ui.vehicles

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*



class Vehicles_( own:CanOwn ) extends FRM(own){

  // tag::fields[]
  given ref: Own[Vehicles_] = Own(this)

  val Vehicles = TBL( _.locator("#VehicleTablePanel"))

  // 
  val `Base Price` = TXT(_.locator("#BasePrice_input"))
  def BasePrice = `Base Price` // alias

  val `Special Price` = TXT(_.locator("#SpecialPrice_input"))
  def SpecialPrice = `Special Price` // alias

  val `Accessories Price` = TXT(_.locator("#AccessoryPrice_input"))
  def AccessoriesPrice = `Accessories Price` // alias

  val Discount = TXT(_.locator("#DiscountValue_input"))

  val `-5%` = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")))
  def FivePercent = `-5%`// alias

  val `Final Price`= TXT(_.locator("#CalculatedPrice_input"))
  def FinalPrice = `Final Price` // alias

  // end::fields[]
}
