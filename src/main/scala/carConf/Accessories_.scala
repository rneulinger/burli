package carConf

import burli.*
import burli.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class Accessories_(own:CanOwn ) extends FRM(own) {

  // tag::fields[]
  given ref: Own[Accessories_] = Own(this)


  val `Add accessories price to final price` = TXT()

  def AddAccessoriesPriceToFinalPrice = `Add accessories price to final price` // alias

  val `Base price` = TXT()

  def BasePrice = `Base price` // alias

  val `Special price` = TXT()

  def SpecialPrice = `Special price` // alias

  val `Accessories price` = TXT()

  def AccessoriesPrice = `Accessories price` // alias

  val Discount = TXT()
  val `Final price` = TXT()

  def FinalPrice = `Final price` // alias


  // end::fields[]
}