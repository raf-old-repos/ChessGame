package com.rafayela.chessgame.pieces

import com.rafayela.chessgame.Board


enum class PieceType {
    PAWN,
    BISHOP,
    ROOK,
    KING,
    QUEEN,
    KNIGHT,
}

enum class Diagonals {
    LF,
    LB,
    RF,
    RB,
    NONE
}


open class BasePiece(startingPosX: Int, startingPosY: Int, type: PieceType, board: Board) {
    var posX = startingPosX
    var posY = startingPosY
    val pieceType = type
    val board = board

    private fun checkOutOfBounds(): Boolean = (((posX or posY) <= 0) || ((posX or posY) > 8))


    // Control functions (redundant for now)

    open fun moveForward(): Unit {
        if (!checkOutOfBounds()) posY++ else println("INTERNAL: OutOfBounds")
    }

    open fun moveBack(): Unit {
        if (!checkOutOfBounds()) posY-- else println("INTERNAL: OutOfBounds")
    }

    open fun moveLeft(steps: Int): Unit {
        if (!checkOutOfBounds()) posX -= steps else println("INTERNAL: OutOfBounds")
    }

    open fun moveRight(steps: Int): Unit {
        if (!checkOutOfBounds()) posX += steps else println("INTERNAL: OutOfBounds")
    }

    open fun moveDiagonalRightForward(steps: Int) {
        if (!checkOutOfBounds()) {
            posX += steps; posY += steps
        } else println("INTERNAL: OutOfBounds")
    }

    open fun moveDiagonalLeftForward(steps: Int) {
        if (!checkOutOfBounds()) {
            posX -= steps; posY += steps
        } else println("INTERNAL: OutOfBounds")
    }

    open fun moveDiagonalRightBackward(steps: Int) {
        if (!checkOutOfBounds()) {
            posX += steps; posY -= steps
        } else println("INTERNAL: OutOfBounds")
    }

    open fun moveDiagonalLeftBackward(steps: Int) {
        if (!checkOutOfBounds()) {
            posX -= steps; posY -= steps
        } else println("INTERNAL: OutOfBounds")
    }

    // Checking if positions are empty (redundant for now)
    open fun checkForward(): Boolean {
        val currentBoardState = board.boardState()
        if (!checkOutOfBounds()) return currentBoardState[posY + 1][posX] != 0 else return false

    }

    open fun checkBack(): Boolean {
        val currentBoardState = board.boardState()
        if (!checkOutOfBounds()) return return currentBoardState[posY - 1][posX] != 0 else return false

    }

    open fun checkRight(): Boolean {
        val currentBoardState = board.boardState()
        if (!checkOutOfBounds()) return currentBoardState[posY][posX + 1] != 0 else return false

    }

    open fun checkLeft(): Boolean {
        val currentBoardState = board.boardState()
        if (!checkOutOfBounds()) return currentBoardState[posY][posX - 1] != 0 else return false

    }

    open fun checkDiagonals(): Diagonals {
        val state = board.boardState()

        // Right forward
        if (!checkOutOfBounds() && state[posY + 1][posX + 1] != 0) {
            return Diagonals.RF
            // Left forward
        } else if (!checkOutOfBounds() && state[posY + 1][posX - 1] != 0) {
            return Diagonals.LF
            // Right back
        } else if (!checkOutOfBounds() && state[posY - 1][posX + 1] != 0) {
            return Diagonals.RB
            // Left Back
        } else if (!checkOutOfBounds() && state[posY - 1][posX - 1] != 0) {
            return Diagonals.LB
        } else {
            return Diagonals.NONE
        }
    }

}



