package com.rafayela.chessgame

import com.rafayela.chessgame.pieces.BasePiece

open class Board {
    var rows = 8;
    var cols = 8;

    open val board: MutableList<MutableList<Any>> = MutableList(rows) { MutableList(cols) {} }

    fun boardState(): MutableList<MutableList<Any>> = board


}
