import java.awt.Canvas
const val RACKET_WIDTH = 90
const val RACKET_SPACER = 530.0
const val RACKET_SECTOR1 = 10
const val RACKET_SECTOR2 = 15
const val RACKET_MAINSECTOR = 40

/**
 * The Racket is divided in 5 different Sectors:
 *              LeftSideS1
 *              LeftSideS2
 *              MainSector
 *              RightSideS2
 *              RightSideS1
 * Each sector applies a different transformation to the ball when hit.
 */
data class Racket(val location: Location)

/**
 * Establishes a limit so that the Racket does not go out of the arena.(RightSide)
 */
fun racketArenaLimitMAX(racket: Racket): Boolean =
    racket.location.x >= ARENA_WIDTH - RACKET_WIDTH

/**
 * Establishes a limit so that the Racket does not go out of the arena.(LeftSide)
 */
fun racketArenaLimitMIN(racket: Racket): Boolean =
    racket.location.x <= 0

/**
 * Creates a racket .
 */
fun initializeRacket(): Racket {
    return Racket(Location(0.0, RACKET_SPACER))
}

/**
 * Applies the needed transformations soo that the racket can move freely, under the imposed limits.
 */
fun computeNextRacket(racket: Racket): Racket {
    return when {
        racketArenaLimitMAX(racket) -> Racket(Location((ARENA_WIDTH - RACKET_WIDTH).toDouble(), racket.location.y))
        racketArenaLimitMIN(racket) -> Racket(Location(0.0, racket.location.y))
        else -> Racket(Location(racket.location.x, racket.location.y))
    }
}


