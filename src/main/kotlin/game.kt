data class Game(
    val ball: List<Ball> = listOf(),
    val racket: Racket
)

/**
 * Initializes a new game with a ball and a racket.
 */

fun initializeGame(): Game {
    val ball: List<Ball> = listOf()
    return Game(ball, initializeRacket())
}

/**
 * Applies transformations to the ball's in the arena, like the reflections on the wall and on the racket.
 * Also keeps the balls moving, if nothing changes their parameters.
 */

fun computeNextBalls(balls: List<Ball>, racket: Racket): List<Ball> {

    val movedballs = balls.map {
        val movedBall = moveBall(it)

        when {
            isOffLimitsX(movedBall) -> invertVelocityX(movedBall)
            isAboveOriginY(movedBall) -> invertVelocityY(movedBall)
            isDeflectedByRacket(movedBall, racket) && LeftSideDefletionS1(
                movedBall,
                racket
            ) -> invertVelocityLeftSideS1(movedBall)
            isDeflectedByRacket(movedBall, racket) && LeftSideDefletionS2(
                movedBall,
                racket
            ) -> invertVelocityLeftSideS2(movedBall)
            isDeflectedByRacket(movedBall, racket) && deflectionMainSector(
                movedBall,
                racket
            ) -> invertVelocityMainSector(movedBall)
            isDeflectedByRacket(movedBall, racket) && RightSideDefletionS2(
                movedBall,
                racket
            ) -> invertVelocityRightSideS2(movedBall)
            isDeflectedByRacket(movedBall, racket) && RightSideDefletionS1(
                movedBall,
                racket
            ) -> invertVelocityRightSideS1(movedBall)
            else -> movedBall
        }

    }.filter {
        it.current.y <= ARENA_HEIGHT
    }

    return movedballs
}

/**
 * Confirms that the ball on the Y coordinate is at the same level as the racket surface.
 */
fun isDeflectedByRacket(ball: Ball, racket: Racket) =
    ball.current.y + BALL_RADIUS >= racket.location.y + RACKET_HEIGHT &&
            ball.velocity.dy >= 0

/**
 * Returns a boolean, that describes if the ball X coordinate, is on the range of values for the LeftSideSector 1.
 */
fun LeftSideDefletionS1(ball: Ball, racket: Racket): Boolean =
    ball.current.x in racket.location.x..racket.location.x + RACKET_SECTOR1

/**
 * Returns a boolean, that describes if the ball X coordinate, is on the range of values for the LeftSideSector 2.
 */
fun LeftSideDefletionS2(ball: Ball, racket: Racket): Boolean =
    ball.current.x in racket.location.x + RACKET_SECTOR1..racket.location.x + RACKET_SECTOR1+RACKET_SECTOR2

/**
 * Returns a boolean, that describes if the ball X coordinate, is on the range of values for the MainSector.
 */
fun deflectionMainSector(ball: Ball, racket: Racket): Boolean =
    ball.current.x in racket.location.x + RACKET_SECTOR1+RACKET_SECTOR2..racket.location.x + RACKET_SECTOR1+RACKET_SECTOR2 + RACKET_MAINSECTOR

/**
 * Returns a boolean, that describes if the ball X coordinate, is on the range of values for the RightSideSector 2.
 */
fun RightSideDefletionS2(ball: Ball, racket: Racket): Boolean =
    ball.current.x in racket.location.x + RACKET_SECTOR1+RACKET_SECTOR2 + RACKET_MAINSECTOR..racket.location.x + RACKET_SECTOR1+RACKET_SECTOR2 + RACKET_MAINSECTOR + RACKET_SECTOR2

/**
 * Returns a boolean, that describes if the ball X coordinate, is on the range of values for the RightSideSector 1.
 */
fun RightSideDefletionS1(ball: Ball, racket: Racket): Boolean =
    ball.current.x in racket.location.x + RACKET_SECTOR1+RACKET_SECTOR2 + RACKET_MAINSECTOR + RACKET_SECTOR2..racket.location.x + RACKET_WIDTH

/**
 * Computes another game with the new parameters for the ball and the racket
 */

fun computeNextGame(game: Game): Game {

    val newBalls: List<Ball> = computeNextBalls(game.ball, game.racket)
    val racket: Racket = computeNextRacket(game.racket)
    return Game(newBalls, racket)
}





