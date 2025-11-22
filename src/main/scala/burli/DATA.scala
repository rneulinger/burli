package burli

abstract class DATA[F <: FRM](b: By)(using ref: Own[F])
  extends ATOM[F](b) {
}