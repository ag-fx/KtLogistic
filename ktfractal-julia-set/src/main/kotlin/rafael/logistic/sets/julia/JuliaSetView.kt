package rafael.logistic.sets.julia

import javafx.scene.control.Spinner
import rafael.logistic.maps.sets.JuliaView
import rafael.logistic.core.fx.configureActions
import rafael.logistic.core.fx.doubleSpinnerValueFactory
import tornadofx.*

class JuliaSetView : JuliaView("Julia Set", "JuliaSet",
    JuliaSetGenerator()
) {

    // @formatter:off

    private     val spnCX               :   Spinner<Double>     by  fxid()
    private     val deltaCXProperty     =   1.toProperty()
    private     val cXValueFactory      =   doubleSpinnerValueFactory(-LIMIT, LIMIT, LIMIT / 2, 0.1)

    private     val spnCY               :   Spinner<Double>     by  fxid()
    private     val deltaCYProperty     =   1.toProperty()
    private     val cYValueFactory      =   doubleSpinnerValueFactory(-LIMIT, LIMIT, LIMIT / 2, 0.1)
    // @formatter:on

    override fun getImageName(): String = "julia" +
            ".XMin=${xMinValueFactory.converter.toString(spnXMin.value)}" +
            ".XMax=${xMaxValueFactory.converter.toString(spnXMax.value)}" +
            ".YMin=${yMinValueFactory.converter.toString(spnYMin.value)}" +
            ".YMax=${yMaxValueFactory.converter.toString(spnYMax.value)}" +
            ".CX=${cXValueFactory.converter.toString(spnCX.value)}" +
            ".CY=${cYValueFactory.converter.toString(spnCY.value)}" +
            ".Iterations_Dot=${spnIterations.value}"

    override fun initializeControls() {
        super.initializeControls()

        super.cXProperty.bind(spnCX.valueProperty())
        spnCX.configureActions(cXValueFactory, deltaCXProperty, this::loadData)

        super.cYProperty.bind(spnCY.valueProperty())
        spnCY.configureActions(cYValueFactory, deltaCYProperty, this::loadData)
    }

}
