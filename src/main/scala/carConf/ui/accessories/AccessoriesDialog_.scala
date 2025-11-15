package carConf.ui.accessories

import burli.*
import burli.bricks.*
import com.microsoft.playwright.*
import com.microsoft.playwright.options.*

class AccessoriesDialog_(own:CanOwn ) extends FRM(own){

  // tag::fields[]
  given ref: Own[AccessoriesDialog_] = Own(this)



  val `Accessory name` = TXT()
  def AccessoryName = `Accessory name` // alias
  val ID = TXT()
  val Price = TXT()


  // end::fields[]
}