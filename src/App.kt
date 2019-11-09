import processing.core.PApplet
import kotlin.math.*

fun main() {
    PApplet.main(App::class.java)
}

fun getDelta(x: Double, y: Double, expr1: (Double, Double) -> (Double), expr2: (Double, Double) -> (Double)) : Double = expr1(x, y) - expr2(x, y)
fun getColor(x: Double, y: Double, expr1: (Double, Double) -> (Double), expr2: (Double, Double) -> (Double)) : Double = max(255.0 - (getDelta(x, y, expr1, expr2)).absoluteValue * 512, 0.0)

class App : PApplet() {
    val expr1: (Double, Double) -> (Double) = { x, y -> cos(x.pow(2)) }
    val expr2: (Double, Double) -> (Double) = { x, y -> cos(y.pow(2)) }

    var currentX = 0
    var currentY = 0

    override fun settings() {
        size(2560 * 2, 1440 * 2)
        noSmooth()
    }

    override fun setup() {
        background(0)
        for (x in 0 until width) {
            for (y in 0 until height) {
                var pixelBrightness = 0f
//                for (xKernel in -1..1) {
//                    for (yKernel in -1..1) {
//                        val newX = x + xKernel / 3.0
//                        val newY = y + yKernel / 3.0
//                        pixelBrightness += getColor((newX - (width / 2)) * .125 * .125, -(newY - (height / 2)) * .125 * .125, expr1, expr2).toFloat()
//                    }
//                }
//                pixelBrightness /= 9.0f
                pixelBrightness = getColor((x - (width / 2)).toDouble() * .125.pow(2), -(y - (height / 2)).toDouble() * .125.pow(2), expr1, expr2).toFloat()
                stroke(pixelBrightness, pixelBrightness, pixelBrightness)
                point(x.toFloat(), y.toFloat())
            }
        }
    }

    override fun draw() {
//        for (i in 0.) {
//            if (currentY < height) {
//                val pixelBrightness: Float = getColor((currentX - (width / 2)).toDouble() * .125 * .25, (currentY - (height / 2)).toDouble() * .125 * .25, expr1, expr2).toFloat()
//                stroke(pixelBrightness, pixelBrightness, pixelBrightness)
//                point(currentX.toFloat(), currentY.toFloat())
//                currentX++
//                if (currentX > width) {
//                    currentX = 0
//                    currentY++
//                }
//            }
//        }
    }

    override fun mousePressed() {
        save("screencap.png")
    }
}