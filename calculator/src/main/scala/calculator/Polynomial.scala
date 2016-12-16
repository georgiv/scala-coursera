package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double], c: Signal[Double]): Signal[Double] = Signal (Math.pow(b(), 2) - 4 * a() * c())

  def computeSolutions(a: Signal[Double], b: Signal[Double], c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = Signal {
    delta() match {
      case d if (d < 0) => Set[Double]()
      case d if (d == 0) => Set(-b() / (2 * a()))
      case d => {
        val r1 = (-b() + Math.sqrt(d)) / (2 * a())
        val r2 = (-b() - Math.sqrt(d)) / (2 * a())
        Set(r1, r2)
      }
    }
  }
}
