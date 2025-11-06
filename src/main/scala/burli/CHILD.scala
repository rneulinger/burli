package burli

import com.microsoft.playwright.options.*

abstract class CHILD
  extends OBJ with IsOwned{
  override final def isRoot = false
  def own:CanOwn
}
