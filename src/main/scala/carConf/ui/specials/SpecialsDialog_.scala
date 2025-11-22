package carConf.ui.specials

import coreo.*

class SpecialsDialog_ ( own:CanOwn ) extends FRM(own){

  // tag::fields[]
  given ref: Own[SpecialsDialog_] = Own(this)

  val Specials = TAB(_.locator("#SpecialsTable"))

  val `Model name` = TXT(_.locator("#SpecialsName99_input"))
  def ModelName = `Model name`
  val Price = TXT(_.locator("#SpecialsPrice99_input"))
  val Description = TXT( _.locator("#SpecialsDialogArea99"))

  val Accessories = TBL( _.locator("#AccessoryTable99") )

  // end::fields[]

}
