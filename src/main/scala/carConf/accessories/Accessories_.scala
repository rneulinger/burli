package carConf.accessories

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class Accessories_(own:CanOwn ) extends FRM(own) {

  // tag::fields[]
  given ref: Own[Accessories_] = Own(this)

  val Accessories = TBL(_.locator("#AccessoryTable"))

  val `Add accessories price to final price` = CBX(_.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Add accessories price to")))

  def AddAccessoriesPriceToFinalPrice = `Add accessories price to final price` // alias

  val `Base price` = TXT(_.locator("#BasePrice_input"))

  def BasePrice = `Base price` // alias

  val `Special price` = TXT(_.getByText("$4,045.00")) // wrong

  def SpecialPrice = `Special price` // alias

  val `Accessories price` = TXT(_.locator("#AccessoryPrice_input"))

  def AccessoriesPrice = `Accessories price` // alias

  val Discount = TXT(_.locator("#DiscountValue_input"))

  val `-5%` = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")))
  def FivePercent = `-5%` // alias

  val `Final price` = TXT(_.getByText("$3,236.00")) // Wrong

  def FinalPrice = `Final price` // alias


  // end::fields[]
}