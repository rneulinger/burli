package burli.bricks

import burli.*

/**
 * has buttons Cancel and Finish
 */
trait CF[F <: FRM]() {
  self: F =>

  def ref: Own[F]

  final val Cancel = BTN[F]()(using ref)
  final val Finish = BTN[F]()(using ref)
}
