package burli.bricks

import burli.*

/**
 * Decorator Button
 * @tparam F owner of these buttons
 */

trait AddEditDeleteMoveUpMoveDown[F <: FRM] extends AddEditDelete[F]{
  self: F =>

  def ref: Own[F]

  // buttons right
  final val MoveUp = BTN[F]()(using ref) // move selected up ! in 1st line
  final val MoveDown = BTN[F]()(using ref) // move selected down ! in last line
}
