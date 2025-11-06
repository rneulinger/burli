package burli

import com.microsoft.playwright.*

abstract class ATOM[F <: FRM](by: By )(using ref:Own[F])
  extends CHILD{
  final def parentType:String = own.getClass.getSimpleName
  final def pg :Page = own.pg
  
  final val own: F = ref.own
  def loc( pg:Page ) : Locator

  def loc: Locator = loc(own.pg)
  //final def pf = own.pg
  /**
   * default locator
   * @return
   */
  //def defaultLocator:Locator
  def myName: String = {

    for (field <- own.getClass.getDeclaredFields) {
      field.setAccessible(true)
      try {
        val value = field.get(own)
        if (value eq this) {
          //println("My name im parent is: " + field.getName)
          return field.getName.
            replaceAll("\\$u0020", " ") // TODO to be improved
        }
      } catch {
        case e: IllegalAccessException =>
          e.printStackTrace()
      }
    }
    "NOT FOUND"
  }
}

