const val BALL_RADIUS = 7

const val BALL_VELOCITY_INITIAL_DY = -4.0

data class Ball(val start:Location, val current: Location, val velocity: Velocity)

/**
 * Creates a ball under the defined parameters of location and speed.
 */
fun createBall(): Ball {
   val start = Location((BALL_RADIUS .. ARENA_WIDTH - BALL_RADIUS).random().toDouble(), ARENA_HEIGHT.toDouble())
   val velocity = Velocity((-6 .. 6).random().toDouble(), BALL_VELOCITY_INITIAL_DY)

   return Ball(start, start, velocity)
}

/**
 *Moves the ball based on it velocity ( Vector addiction ).
 */
fun moveBall(ball:Ball) = Ball(
    ball.start,
    move(ball.current, ball.velocity),
    ball.velocity
)

/**
 * Creates another ball in the arena.
 */
fun createBallInArena(game:Game): Game{
    val ballList : List<Ball> = game.ball + createBall()

    return Game(ballList, game.racket)
}
