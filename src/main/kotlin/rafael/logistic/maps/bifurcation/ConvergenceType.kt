package rafael.logistic.maps.bifurcation

const val END_CONSTANT = 3.00 - 0.00
const val END_CYCLE_2 = 3.449489742783178 // = 1.0 + sqrt(6)
const val END_VALUE = 4.000000000001

enum class ConvergenceType {
    ZERO, CONSTANT, CYCLE_2, CHAOS;

    companion object {

        fun valueOf(r: Double) =
                when (r) {
                    in 0.0..1.0                  -> ZERO
                    in 1.0..END_CONSTANT         -> CONSTANT
                    in END_CONSTANT..END_CYCLE_2 -> CYCLE_2
                    in END_CYCLE_2..END_VALUE    -> CHAOS
                    else                         -> error("Invalid r: $r")
                }

    }
}