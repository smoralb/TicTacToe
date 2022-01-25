package com.smb.components.ui.mocks

data class GameBoardClassMock(
    var title: String,
    var result: Boolean,
    var row: Int,
    var col: Int,
    var type: WinnerType
)

enum class WinnerType {
    VERTICAL, HORIZONTAL, DIAGONAL, REVERSE_DIAGONAL, BOX
}