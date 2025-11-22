package carConf.specials

import coreo.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class Specials_ ( own:CanOwn ) extends FRM(own)
 with coreo.bricks.NewChangDeleteDetailsCancelOK {

  // tag::fields[]
  given ref: Own[Specials_] = Own(this)

  val Specials = CBX(_.locator("#SpecialsCombo"))
  val Special = TXT(_.locator("#SpecialsArea"))
  //
  val `Base Price` = TXT(_.locator("#BasePrice_input"))
  def BasePrice = `Base Price`

  val `Special Price` = TXT(_.getByText("$4,045.00")) // Wrong
  def SpecialPrice = `Special Price`

  val `Accessories Price` = TXT(_.locator("#AccessoryPrice_input"))
  def AccessoriesPrice = `Accessories Price`

  val Discount = TXT(_.locator("#DiscountValue_input"))

  val `-5%` = BTN(_.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("-5%")))
  def FivePercent = `-5%` // alias


  val `Final Price` = TXT(_.getByText("$3,438.25"))  // Wrong
  def FinalPrice = `Final Price`
  // end::fields[]

}
