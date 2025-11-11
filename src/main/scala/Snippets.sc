import scala.collection.JavaConverters._

val inp =
  """
  Vehicles
  Vehicle name
  ID
  Price
  New
  Change
  Delete
  OK
  Cancel
  """.stripMargin

val fields = inp.lines.iterator.asScala.
  mkString(",").split(",").toList.map(_.trim).filter(_.nonEmpty)

if fields.size != fields.toSet.size then
  throw IllegalArgumentException("Duplicate(s) in field(s) definitions")

def mkCamelCase(s:String) = {
  val clean = s.trim.replace("-", " ") // a few more to come
  val chunks= clean.split("\\s+").toList
  val res = for ( s <- chunks) yield
    val first = s.take(1).toUpperCase()
    val rest = s.drop(1)
    first + rest

  res.mkString("")
}



def declFields() = {
  // TODO there can be still a duplicate conflict in aliases
  for ( fl <- fields.filter(_.nonEmpty)) {
    val f = (fl, mkCamelCase(fl))
    if( f._1 == f._2){ // mo alias
      println( s"  val ${f._1} = TXT()")
    } else {
      println( s"  val `${f._1}` = TXT()")
      println( s"  def ${f._2} = `${f._1}` // alias")
    }
  }
}
println(
  """import burli.*
    |import burli.bricks.*
    |import com.microsoft.playwright.*
   |import com.microsoft.playwright.options.*
    |
    |class MyFrm_ ( own:CanOwn ) extends FRM(own){
    |
    |  // tag::fields[]
    |  given ref: Own[MyFrm_] = Own(this)
    |""".stripMargin)

declFields()

println(
  """
    |  // end::fields[]
    |}
    |
    |""".stripMargin)

