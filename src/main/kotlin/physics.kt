
data class Location(val x: Double, val y: Double)

data class Velocity(val dx: Double, val dy: Double)

/**
 * Adds to a location a value of velocity.
 */
fun move(start: Location, velocity: Velocity) =
    Location(start.x + velocity.dx, start.y + velocity.dy)

/**
 * Confirms that the ball is outside the limits in the sides of the arena.
 */
fun isOffLimitsX(ball: Ball): Boolean =
    ball.current.x + BALL_RADIUS >= ARENA_WIDTH|| ball.current.x - BALL_RADIUS <= 0

/**
 * Confirms that the ball is outside the limits at the top of the arena.
 */
fun isAboveOriginY(movedBall: Ball) = movedBall.current.y - BALL_RADIUS <= 0

/**
 * Inverts the velocity to meet the desired parameters if the ball hits the sides of the arena.
 */
fun invertVelocityX(ball: Ball): Ball {

    return Ball(ball.start, ball.current, Velocity(-ball.velocity.dx, ball.velocity.dy))
}

/**
 * Inverts the velocity to meet the desired parameters if the ball hits the top of the arena.
 */
fun invertVelocityY(ball: Ball): Ball {

    return Ball(ball.start, ball.current, Velocity(ball.velocity.dx, -ball.velocity.dy))
}

/**
 * Inverts the velocity to meet the desired parameters if the ball hits the LeftSideS1 on the Racket.
 */
fun invertVelocityLeftSideS1(ball: Ball) : Ball {

    return Ball(ball.start, ball.current, Velocity(ball.velocity.dx - 3, -ball.velocity.dy))
}

/**
 * Inverts the velocity to meet the desired parameters if the ball hits the RightSideS1 on the Racket.
 */
fun invertVelocityRightSideS1(ball: Ball) : Ball {

    return Ball(ball.start, ball.current, Velocity(ball.velocity.dx + 3, -ball.velocity.dy))
}

/**
 * Inverts the velocity to meet the desired parameters if the ball hits the LeftSideS2 on the Racket.
 */
fun invertVelocityLeftSideS2(ball: Ball) : Ball {

    return Ball(ball.start, ball.current, Velocity(ball.velocity.dx - 1, -ball.velocity.dy))
}

/**
 * Inverts the velocity to meet the desired parameters if the ball hits the RightSideS2 on the Racket.
 */
fun invertVelocityRightSideS2(ball: Ball) : Ball {

    return Ball(ball.start, ball.current, Velocity(ball.velocity.dx + 1, -ball.velocity.dy))
}

/**
 * Inverts the velocity to meet the desired parameters if the ball hits the MainSector on the Racket.
 */
fun invertVelocityMainSector(ball: Ball) : Ball {

    return Ball(ball.start, ball.current, Velocity(ball.velocity.dx, -ball.velocity.dy))
}


