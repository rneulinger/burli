package carConf

import burli.*

class Vehicles_( own:CanOwn ) extends FRM(own){

  // tag::fields[]
  given ref: Own[Vehicles_] = Own(this)

  val Grid = TBL()

  // 
  val `Base Price` = TXT()
  def BasePrice = `Base Price`
  val `Special Price` = TXT()
  def SpecialPrice = `Special Price`
  val `Accessories Price` = TXT()
  def AccessoriesPrice = `Accessories Price`
  val Discount = TXT()
  val `Final Price`= TXT()
  def FinalPrice = `Final Price`

  // end::fields[]
}
