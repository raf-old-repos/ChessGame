package com.rafayela.chessgame

import com.rafayela.chessgame.pieces.*


open class Board {

    private var board: MutableList<MutableList<BasePiece?>> = mutableListOf(
        mutableListOf(
            null
        )
    )


    fun boardState(): MutableList<MutableList<BasePiece?>> = board

    open fun drawBoard() {
        // white pawns and black pawns
        for (i in 0..8) {
            // white

            board[1][i] = Pawn(i, 0, this, Identifier.WHITE)

            // black

            board[6][i] = Pawn(i, 6, this, Identifier.BLACK)
        }


    }

    open fun redrawBoard() {
        board.forEachIndexed { colsIdx, cols ->
            cols.forEachIndexed { pieceIdx, piece ->

                if (piece == null) return
                // old position
                board[colsIdx][pieceIdx] = null


                // new position
                board[piece.posY][piece.posX] = piece

            }
        }
    }


}
