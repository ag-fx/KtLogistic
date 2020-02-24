package rafael.logistic.view.iterationchart

import javafx.beans.NamedArg
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.collections.ObservableList
import javafx.scene.chart.Axis
import javafx.scene.shape.LineTo
import javafx.scene.shape.MoveTo
import javafx.scene.shape.PathElement
import rafael.logistic.generator.BiPoint
import tornadofx.*

class IterationChartBi(
        @NamedArg("xAxis") xAxis: Axis<Int>,
        @NamedArg("yAxis") yAxis: Axis<Double>,
        @NamedArg("data") data: ObservableList<Series<Int, Double>>) : IterationChartBase<BiPoint>(xAxis, yAxis, data) {

    constructor(@NamedArg("xAxis") xAxis: Axis<Int>, @NamedArg("yAxis") yAxis: Axis<Double>) :
            this(xAxis, yAxis, mutableListOf<Series<Int, Double>>().observable())

    companion object {
        val extractorX: (BiPoint) -> Double = BiPoint::x
        val extractorY: (BiPoint) -> Double = BiPoint::y
    }

    private var extractor: (BiPoint) -> Double = { kotlin.error("") }

    fun bind(valueProperty: ReadOnlyObjectProperty<Int>, observableData: ReadOnlyObjectProperty<List<BiPoint>>, _extractor: (BiPoint) -> Double) {
        super.bind(valueProperty, observableData)
        this.extractor = _extractor
    }

    override fun loadPath(iterationData: List<BiPoint>): Array<PathElement> {
        val positions: List<Pair<Int, Double>> = iterationData
                .mapIndexed { i, pt -> Pair(i, extractor(pt)) }
                .filter { pair -> pair.second >= valueYAxis.lowerBound && pair.second <= valueYAxis.upperBound }

        return positions.indices
                .map { index -> Triple(index == 0 || positions[index].first - positions[index - 1].first > 1, positions[index].first.toIterationsXPos(), positions[index].second.toIterationsYPos()) }
                .map { t -> if (t.first) MoveTo(t.second, t.third) else LineTo(t.second, t.third) }
                .toTypedArray()
    }

}