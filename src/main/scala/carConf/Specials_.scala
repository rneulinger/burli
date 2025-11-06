package carConf

import burli.*

class Specials_ ( own:CanOwn ) extends FRM(own){

  // tag::fields[]
  given ref: Own[Specials_] = Own(this)

  val Combo = CBX()
  //
  val `Base Price` = TXT()
  def BasePrice = `Base Price`

  val `Special Price` = TXT()
  def SpecialPrice = `Special Price`

  val `Accessories Price` = TXT()
  def AccessoriesPrice = `Accessories Price`

  val Discount = TXT()

  val `Final Price` = TXT()
  def FinalPrice = `Final Price`
  // end::fields[]

}
