package burli

import com.microsoft.playwright.*

abstract class FRM(override val own: CanOwn, typ:String = "")
  extends CHILD with CanOwn {

  def path: String = "" //

  val fullType:String = if ( typ.isEmpty ) myType else  typ
  own.adopt(this)


  override def pg: Page = own.pg

  private var adoptedAtoms = List[ATOM[?]]()
  lazy val atoms: Map[String, ATOM[?]] = {
    val tmp = adoptedAtoms.map(a => a.fullName -> a).toMap
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

  private def atomsMax = if atoms.isEmpty then 0 else atoms.keySet.map(_.length).max
  private def shortMax = if atoms.isEmpty then 0 else atoms.values.map(_.fullName.length).max
  private def typeMax = if atoms.isEmpty then 0 else atoms.values.map(_.myType.length).max
  private val BLANK = " ".charAt(0)

  def mkAdd: String = {
    val head = s"""|| ${"name".padTo(atomsMax, " ".charAt(0))} |   | typ |"""
    val lines = for (a <- atoms.filterNot(_._2.isInstanceOf[BTN[_]])) yield {
      s"""|| ${a._1.padTo(atomsMax, BLANK)} |   | ${a._2.myType.padTo(typeMax, BLANK)} |"""
    }
    val tbl = List(head) ::: lines.toList
    val res =
      s"""
         |* add: '$fullType'
      ${tbl.mkString("\n")}
         |""".stripMargin
    Defs.toClipboard(res)
  }

  def mkEdit: String = {
    val head = s"""|| ${"name".padTo(atomsMax, " ".charAt(0))} |   | typ |"""
    val lines = for (a <- atoms.filterNot(_._2.isInstanceOf[BTN[_]])) yield {
      s"""|| ${a._1.padTo(atomsMax, BLANK)} |   | ${a._2.myType.padTo(typeMax, BLANK)} |"""
    }
    val tbl = List(head) ::: lines.toList
    val res =
      s"""
         |* edit: '$fullType'
      ${tbl.mkString("\n")}
         |""".stripMargin
    Defs.toClipboard(res)
  }

  def mkNext: String = {
    val head = s"""|| ${"name".padTo(atomsMax, " ".charAt(0))} |   | typ |"""
    val lines = for (a <- atoms.filterNot(_._2.isInstanceOf[BTN[_]])) yield {
      s"""|| ${a._1.padTo(atomsMax, BLANK)} |   | ${a._2.myType.padTo(typeMax, BLANK)} |"""
    }
    val tbl = List(head) ::: lines.toList
    val res =
      s"""
         |* next: '$fullType'
      ${tbl.mkString("\n")}
         |""".stripMargin
    Defs.toClipboard(res)
  }

  def mkSet:String = {
    val head = s"""|| ${"name".padTo(atomsMax," ".charAt(0))} |   | typ |"""
    val lines = for (a <- atoms.filterNot(_._2.isInstanceOf[BTN[_]]) ) yield {
      s"""|| ${a._1.padTo(atomsMax,BLANK)} |   | ${a._2.myType.padTo(typeMax,BLANK)} |"""
    }
    val tbl = List(head) ::: lines.toList
    val res = s"""
      |* set: '$fullType'
      ${tbl.mkString("\n")}
      |""".stripMargin
    Defs.toClipboard(res)
  }

  def mkAct:String = {
    val head = s"""|| ${"name".padTo(atomsMax," ".charAt(0))} | op | p1 | p2 | p3 | typ |"""
    val lines = for (a <- atoms) yield {
      s"""|| ${a._1.padTo(atomsMax,BLANK)} |    |    |    |    | ${a._2.myType.padTo(typeMax,BLANK)} |"""
    }
    val tbl = List(head) ::: lines.toList
    val res = s"""
                 |* act: '$fullType'
      ${tbl.mkString("\n")}
                 |""".stripMargin
    Defs.toClipboard(res)
  }

  def mkGet:String = {
    val head = s"""|| ${"name".padTo(atomsMax,BLANK)} | op | ${"var".padTo(shortMax,BLANK)} |"""
    val lines = for (a <- atoms.filterNot(_._2.isInstanceOf[BTN[_]])) yield {
      s"""|| ${a._1.padTo(atomsMax,BLANK)} |    | ${a._2.shortName.padTo(shortMax,BLANK)} |"""
    }
    val tbl = List(head) ::: lines.toList
    val res = s"""
       |* get: '$fullType'
       ${tbl.mkString("\n")}
       |""".stripMargin
    Defs.toClipboard(res)
  }

  def mkChk:String = {
    val head = s"""|| ${"name".padTo(atomsMax," ".charAt(0))} | op | ref |"""
    val lines = for (a <- atoms) yield {
      s"""|| ${a._1.padTo(atomsMax,BLANK)} | == |     |"""
    }
    val tbl = List(head) ::: lines.toList
    val res = s"""
       |* chk: '$fullType'
       ${tbl.mkString("\n")}
       |""".stripMargin

    Defs.toClipboard(res)

  }

  /**
   * c sharp erzeugen
   */
  def mkCs:String = {
    val lines = for (a <- atoms) yield {
      val name = a._2.fullName
      val short = Defs.mkCamelCase(name)
      val typ = a._2.myType
      s"""|        $short = new $typ(this, \"$name\");"""
    }
    val decls = for (a <- atoms) yield {
      val name = a._2.fullName
      val short = Defs.mkCamelCase(name)
      val typ = a._2.myType
      s"""|    public readonly $typ $short;"""
    }

    val res = s"""namespace Generic;
       |
       |using Core;
       |// ReSharper disable InconsistentNaming
       |public class ${myType}_ : FRM{
       ${decls.mkString("\n")}
       |
       |  public ${myType}_( CanOwn own ):base(own) {
          ${lines.mkString("\n")}
       |  }
       |}
       |// ${myType}_ _$myType = new $myType( this );
       |""".stripMargin
    Defs.toClipboard(res)
  }

  /**
   * typescript sharp erzeugen
   */
  def mkTs:String = {
    val Core="Core"
    val lines = for (a <- atoms) yield {
      val name = a._2.fullName
      val short = Defs.mkCamelCase(name)
      val typ = a._2.myType
      s"""|        this.$short = new $Core.$typ(this, \"$name\");"""
    }
    val decls = for (a <- atoms) yield {
      val name = a._2.fullName
      val short = Defs.mkCamelCase(name)
      val typ = a._2.myType
      s"""|    public readonly $short : $Core.$typ;"""
    }

    val res = s"""namespace Generic{
                 |
                 |//import {BTN,TXT,FRM,CanOwn} from '../Core';
                 |
                 |export class ${myType}_ extends $Core.FRM{
       ${decls.mkString("\n")}
                 |
                 |  constructor( own:$Core.CanOwn ){
                 |    super(own)
          ${lines.mkString("\n")}
                 |  }
                 |}
                 |// ${myType}_ _$myType = new $myType( this );
                 |}
                 |""".stripMargin
    Defs.toClipboard(res)
  }

  /**
   * create asciidoc snipped (copie to clipboard)
   * @return
   */
  def mkDoc:String= {
    def mkLink = if path.isEmpty then "" else s"* http{ip}/$path[]"
    def mkInc = if path.isEmpty then "" else s"$path/"
    def mkArrow = if path.isEmpty then "" else " ->"
    val res = s"""
       |=== $myType$mkArrow
       |
       |$path
       |
       |image::VoIP.png[]
       |
       |[,java]
       |----
       |include::{SRI}/$mkInc${myType}_.scala[tag=fields]
       |----
       |
       |
       |""".stripMargin
    Defs.toClipboard(res)
    res
  }
}
