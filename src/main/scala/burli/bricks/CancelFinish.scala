package burli.bricks

import burli.*
trait CancelFinish[F <: FRM]() {
  self: F =>

  def ref: Own[F]

  final val Cancel = BTN[F]()(using ref)
  final val Finish = BTN[F]()(using ref)
}
