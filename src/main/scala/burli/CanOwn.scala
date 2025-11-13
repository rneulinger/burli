package burli

import com.microsoft.playwright.*

/**
 * either Root or FRM
 */
trait CanOwn {
  /**
   * associated page
   *
   * @return
   */
  def pg: Page

  /**
   * convenience method, to use gode generato rsnippet directly
   *
   * @return
   */
  final def page: Page = pg

  def adopt(obj: OBJ): Unit

  def openUrl(path: String): Unit

  def onto( frm:FRM):Unit

}
