import pt.isel.canvas.*

const val RACKET_HEIGHT = 10
private const val FONT_SIZE = 45

/**
 * Draws everything needed to represent visually the game.
 */
fun drawGame(arena:Canvas, game:Game){
    arena.erase()
    game.ball.forEach {drawBall(arena,it)}
    drawCounter(arena, game)
    drawRacket(arena,game.racket)
}

private fun drawCounter(arena: Canvas, game:Game) {
    arena.drawText(
        185,595, game.ball.size.toString(), WHITE, FONT_SIZE
    )
}

private fun drawBall(arena:Canvas, ball: Ball) {
    arena.drawCircle(
        ball.current.x.toInt(),
        ball.current.y.toInt(),
        BALL_RADIUS,
        CYAN
    )
}

private fun drawRacket(arena:Canvas, racket: Racket) {
    arena.drawRect(racket.location.x.toInt(), racket.location.y.toInt(),10,RACKET_HEIGHT, RED)
    arena.drawRect(racket.location.x.toInt() + 10, racket.location.y.toInt(),15,RACKET_HEIGHT, MAGENTA)
    arena.drawRect(racket.location.x.toInt() + 25, racket.location.y.toInt(), 40, RACKET_HEIGHT, WHITE)
    arena.drawRect(racket.location.x.toInt() + 65, racket.location.y.toInt(),15,RACKET_HEIGHT, MAGENTA )
    arena.drawRect(racket.location.x.toInt() + 80, racket.location.y.toInt(),10,RACKET_HEIGHT, RED)
    arena.drawRect(racket.location.x.toInt() , racket.location.y.toInt() + RACKET_HEIGHT / 2,90,RACKET_HEIGHT / 2, WHITE)
}