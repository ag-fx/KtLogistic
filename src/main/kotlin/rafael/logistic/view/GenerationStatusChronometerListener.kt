package rafael.logistic.view

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import java.time.Duration
import java.time.LocalTime

class GenerationStatusChronometerListener : ChangeListener<GenerationStatus> {

    private var priorTime: LocalTime? = null

    override fun changed(observable: ObservableValue<out GenerationStatus>?, oldValue: GenerationStatus?, newValue: GenerationStatus?) {
        val now = LocalTime.now()

        if (priorTime == null) {
            println("[%s] %20s -> %20s".format(now, oldValue, newValue))
        } else {
            println("[%s] %20s -> %20s: %4d ms".format(now, oldValue, newValue, Duration.between(priorTime, now).toMillis()))
        }

        priorTime = if (newValue == GenerationStatus.IDLE) null else now
    }
}