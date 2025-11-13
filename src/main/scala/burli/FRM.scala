package burli

import com.microsoft.playwright.*

abstract class FRM(override val own: CanOwn)
  extends CHILD with CanOwn {
  own.adopt(this)

  def path: String = "" //
  //final def goto(path:String="/"):Unit = gotoPath(path)

  //  final def navigate:Unit = goto(path)
  //
  override def pg: Page = own.pg

  private var adoptedAtoms = List[ATOM[?]]()
  lazy val atoms: Map[String, ATOM[?]] = {
    val tmp = adoptedAtoms.map(a => a.myName -> a).toMap
    tmp
  }

  final def adopt(obj: OBJ): Unit =
    obj match {
      case atom: ATOM[_] => adoptedAtoms = adoptedAtoms.appended(atom)
      case frm: FRM => own.adopt(obj)
    }

  def goto(): FRM = {
    own.onto(this)
    openUrl(path)
    Thread.sleep(1000)
    this
  }

  def onto(): FRM = {
    own.onto(this)
    Thread.sleep(200)
    this
  }

  final def openUrl(path: String): Unit = {
    own.openUrl(path)
  }

  def onto( frm:FRM):Unit = own.onto(frm)

  def find( name:String ): Option[ATOM[?]] =
    if (atoms.keySet.contains(name)){
      Option(atoms(name))
    } else {
      None
    }

  def dump( string :String= ""): Unit = {
    println(string + myType + "  " + path)

    for (atom <- atoms) {
      val len = atoms.map(_._1.length).max
      val name = atom._1
      println("\t" + name + " " * (len - name.length) + " : " + atom._2)
    }
  }

  def mkSet:String = {
    val lines = for (a <- atoms) yield {
      s"""|| ${a._1} | = |   |"""
    }
    s"""* I am onto '$myType'
      |* set:
      ${lines.mkString("\n")}
      |""".stripMargin
  }

  def mkGet:String = {
    val lines = for (a <- atoms) yield {
      s"""|| ${a._1} | => | ${a._1} |"""
    }
    s"""* I am onto '$myType'
       |* get:
       ${lines.mkString("\n")}
       |""".stripMargin
  }
  def mkChk:String = {
    val lines = for (a <- atoms) yield {
      s"""|| ${a._1} | == |   |"""
    }
    s"""* I am onto '$myType'
       |* chk:
       ${lines.mkString("\n")}
       |""".stripMargin

  }

  /**
   * c sharp erzeugen
   */
  def mkCs:String = {
    val lines = for (a <- atoms) yield {
      s"""|  public final ${a._2.myType} ${a._2.myName} = new ${a._2.myName}(this);"""
    }

    s"""public class $myType : FRM{
       |  public $myType( CanOwn own ) {
       |    super( own );
       |  }
       |${lines.mkString("\n")}
       |}
       |""".stripMargin
  }
}
