package com.rafayela.chessgame.pieces

import com.rafayela.chessgame.Board
import jdk.jshell.Diag


open class Pawn(startingPosX: Int, startingPosY: Int, board: Board, identifier: Identifier) :
    BasePiece(startingPosX, startingPosY, type = PieceType.PAWN, board, identifier) {


    open fun move(steps: Int) {
        if (identity == Identifier.WHITE && super.posY == 1) {
            if (steps <= 2) super.moveForward(steps) else println("Illegal move (WHITE): Cannot move more than 2 steps at starting row")
        } else if (identity == Identifier.BLACK && super.posY == 6) {
            if (steps <= 2) super.moveBack(steps) else println("Illegal move (BLACK): Cannot move more than 2 steps at starting row")
        } else {
            if (steps == 1) {
                if (identity == Identifier.WHITE) {
                    super.moveForward(steps)
                } else if (identity == Identifier.BLACK) {
                    super.moveBack(steps)
                }
            } else {
                println("Illegal move: Cannot move more than one step")
            }
        }
    }

    open fun eat(direction: Diagonals) {
        val openDiagonals = super.checkDiagonals()

        if (openDiagonals.contains(direction)) {
            val board = currBoard.boardState()
            if (identity == Identifier.WHITE && (direction == Diagonals.LF) or (direction == Diagonals.RF)) {
                when (direction) {
                    Diagonals.LF -> if (board[posY + 1][posX - 1]?.identity == Identifier.WHITE) return println("Illegal move: Cannot conquer own pawn")
                    Diagonals.RF -> if (board[posY + 1][posX + 1]?.identity == Identifier.WHITE) return println("Illegal move: Cannot conquer own pawn")
                }
                super.moveDiagonal(1, direction)
            } else if (identity == Identifier.BLACK && (direction == Diagonals.LB) or (direction == Diagonals.RB)) {
                when (direction) {
                    Diagonals.LB -> if (board[posY - 1][posX - 1]?.identity == Identifier.BLACK) return println("Illegal move: Cannot conquer own pawn")
                    Diagonals.RB -> if (board[posY - 1][posX + 1]?.identity == Identifier.BLACK) return println("Illegal move: Cannot conquer own pawn")
                }
                super.moveDiagonal(1, direction)
            }
        } else println("Cannot Eat: No piece to conquer :(")
    }
}