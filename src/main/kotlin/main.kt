import pt.isel.canvas.*

const val ARENA_COLOR = BLACK
const val ARENA_WIDTH = 400
const val ARENA_HEIGHT = 600

const val ARENA_MAXBALLS = 7



fun main() {

    onStart {

        val arena = Canvas(ARENA_WIDTH, ARENA_HEIGHT, ARENA_COLOR)
        var game = initializeGame()

        arena.onMouseMove {
            game = Game(game.ball, Racket((Location(it.x.toDouble() - RACKET_WIDTH / 2, RACKET_SPACER))))
        }

        arena.onTimeProgress(5000){
            game = createBallInArena(game)
            if (game.ball.size >= ARENA_MAXBALLS)
                arena.close()

        }

        arena.onTimeProgress(10){

            game=computeNextGame(game)
            drawGame(arena, game)

        }
    }

    onFinish {
    }
}


