package coreo

abstract class ACTION[F <: FRM](b: By)(using ref: Own[F])
  extends ATOM[F](b) {
}