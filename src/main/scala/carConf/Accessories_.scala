package carConf

import burli.*

class Accessories_ ( own:CanOwn ) extends FRM(own){

  // tag::fields[]
  given ref: Own[Accessories_] = Own(this)

  // end::fields[]

}
