package burli

import com.microsoft.playwright.*

abstract class FRM(override val own:CanOwn)
  extends CHILD with CanOwn{
  def path:String = "" //
  //final def goto(path:String="/"):Unit = gotoPath(path)

//  final def navigate:Unit = goto(path)
//
  override def pg: Page = own.pg

}
