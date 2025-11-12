package burli

import com.microsoft.playwright.*

abstract class FRM(override val own:CanOwn)
  extends CHILD with CanOwn{
  own.adopt(this)
  def path:String = "" //
  //final def goto(path:String="/"):Unit = gotoPath(path)

//  final def navigate:Unit = goto(path)
//
  override def pg: Page = own.pg

  private var adoptedAtoms = List[ATOM[?]]()
  lazy val atoms : Map[String, ATOM[?]] = {
    val tmp = adoptedAtoms.map(a => a.myName -> a).toMap
    tmp
  }
  override def adopt(obj: OBJ): Unit =
    obj match{
      case atom:ATOM[_] => adoptedAtoms = adoptedAtoms.appended(atom)
      case frm:FRM => own.adopt(obj)
    }
}
