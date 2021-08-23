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

enum class Identifier {
    WHITE,
    BLACK
}


open class BasePiece(startingPosX: Int, startingPosY: Int, type: PieceType, board: Board, identifier: Identifier) {
    var posX: Int = startingPosX
    var posY: Int = startingPosY
    val pieceType: PieceType = type
    val currBoard: Board = board
    val identity: Identifier = identifier

    /*   init {
           posX = startingPosX
           posY = startingPosY
           pieceType = type
           currBoard = board
           identity = identifier
       }
   */
    private fun checkOutOfBounds(): Boolean = (((posX or posY) <= 0) || ((posX or posY) > 8))


    // Control functions (redundant for now)

    open fun moveForward(steps: Int): Unit {
        if (!checkOutOfBounds()) posY += steps else println("INTERNAL: OutOfBounds")
    }

    open fun moveBack(steps: Int): Unit {
        if (!checkOutOfBounds()) posY -= steps else println("INTERNAL: OutOfBounds")
    }

    open fun moveLeft(steps: Int): Unit {
        if (!checkOutOfBounds()) posX -= steps else println("INTERNAL: OutOfBounds")
    }

    open fun moveRight(steps: Int): Unit {
        if (!checkOutOfBounds()) posX += steps else println("INTERNAL: OutOfBounds")
    }

    open fun moveDiagonal(steps: Int, direction: Diagonals) {
        if (!checkOutOfBounds()) {
            when (direction) {
                Diagonals.LF -> {
                    posX -= steps; posY += 1
                }
                Diagonals.RF -> {
                    posX += steps; posY += 1
                }
                Diagonals.LB -> {
                    posX -= steps; posY -= 1
                }
                Diagonals.RB -> {
                    posX += steps; posY -= 1
                }
            }
        } else println("INTERNAL: OutOfBounds")
    }


    // Checking if positions are empty (redundant for now)
    open fun checkForward(): Boolean {
        val currentBoardState = currBoard.boardState()
        if (!checkOutOfBounds()) return currentBoardState[posY + 1][posX] != null else return false

    }

    open fun checkBack(): Boolean {
        val currentBoardState = currBoard.boardState()
        if (!checkOutOfBounds()) return currentBoardState[posY - 1][posX] != null else return false

    }

    open fun checkRight(): Boolean {
        val currentBoardState = currBoard.boardState()
        if (!checkOutOfBounds()) return currentBoardState[posY][posX + 1] != null else return false

    }

    open fun checkLeft(): Boolean {
        val currentBoardState = currBoard.boardState()
        if (!checkOutOfBounds()) return currentBoardState[posY][posX - 1] != null else return false

    }

    open fun checkDiagonals(): MutableList<Diagonals> {
        val state = currBoard.boardState()

        val diagonalsList: MutableList<Diagonals> = mutableListOf()
        // Right forward
        if (!checkOutOfBounds() && state[posY + 1][posX + 1] != null) {
            diagonalsList.add(Diagonals.RF)
            // Left forward
        } else if (!checkOutOfBounds() && state[posY + 1][posX - 1] != null) {
            diagonalsList.add(Diagonals.LF)
            // Right back
        } else if (!checkOutOfBounds() && state[posY - 1][posX + 1] != null) {
            diagonalsList.add(Diagonals.RB)
            // Left Back
        } else if (!checkOutOfBounds() && state[posY - 1][posX - 1] != null) {
            diagonalsList.add(Diagonals.LB)
        } else {
            diagonalsList.add(Diagonals.NONE)
        }
        return diagonalsList
    }

}



