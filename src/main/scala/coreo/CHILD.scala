package coreo

import com.microsoft.playwright.options.*

abstract class CHILD
  extends OBJ with IsOwned {

  def own: CanOwn
}
