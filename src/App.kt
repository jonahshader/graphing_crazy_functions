import processing.core.PApplet
import kotlin.math.*

fun main() {
    PApplet.main(App::class.java)
}

fun getDelta(x: Double, y: Double, expr1: (Double, Double) -> (Double), expr2: (Double, Double) -> (Double)) : Double = expr1(x, y) - expr2(x, y)
fun getColor(x: Double, y: Double, expr1: (Double, Double) -> (Double), expr2: (Double, Double) -> (Double)) : Double = max(255.0 - (getDelta(x, y, expr1, expr2)).absoluteValue * 256, 0.0)

class App : PApplet() {
    val expr1: (Double, Double) -> (Double) = { x, y -> cos(x)/y }
    val expr2: (Double, Double) -> (Double) = { x, y -> cos(y)/x }

    var currentX = 0
    var currentY = 0

    override fun settings() {
        size(512, 512)
        noSmooth()
    }

    override fun setup() {
        background(0)
    }

    override fun draw() {
        for (i in 0..1024) {
            if (currentY < height) {
                val pixelBrightness: Float = getColor((currentX - (width / 2)).toDouble() * .125 * .25, (currentY - (height / 2)).toDouble() * .125 * .25, expr1, expr2).toFloat()
                stroke(pixelBrightness, pixelBrightness, pixelBrightness)
                point(currentX.toFloat(), currentY.toFloat())
                currentX++
                if (currentX > width) {
                    currentX = 0
                    currentY++
                }
            }
        }
    }
}