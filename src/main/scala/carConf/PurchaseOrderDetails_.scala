package carConf

import burli.*
import burli.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class PurchaseOrderDetails_ ( own:CanOwn ) extends FRM(own){

  // tag::fields[]
  given ref: Own[PurchaseOrderDetails_] = Own(this)



  val `Selected vehicle` = TXT()
  def SelectedVehicle = `Selected vehicle` // alias
  val Price = TXT()
  val Discount = TXT()
  val Total = TXT()
  val `Selected special` = TXT()
  def SelectedSpecial = `Selected special` // alias
  val `Price special details` = TXT()
  def PriceSpecialDetails = `Price special details` // alias
  val `Selected accessory` = TXT()
  def SelectedAccessory = `Selected accessory` // alias
  val Result = TXT()
  val `Final price` = TXT()
  def FinalPrice = `Final price` // alias
  val `Collection ex works` = TXT()
  def CollectionExWorks = `Collection ex works` // alias
  val `Collection ex vendor` = TXT()
  def CollectionExVendor = `Collection ex vendor` // alias
  val Deliver = TXT()
  val `Send purchas eorder` = TXT()
  def SendPurchasEorder = `Send purchas eorder` // alias
  val Cancel = TXT()


  // end::fields[]
}