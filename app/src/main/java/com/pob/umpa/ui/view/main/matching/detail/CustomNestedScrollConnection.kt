package com.pob.umpa.ui.view.main.matching.detail

import androidx.compose.foundation.ScrollState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource

class CustomNestedScrollConnection(
    private val isHeaderHide: Boolean,
    private val scrollState: ScrollState,
) : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val dy = available.y

        return when {
            isHeaderHide -> Offset.Zero

            dy < 0 -> {
                scrollState.dispatchRawDelta(dy * -1)
                Offset(0f, dy)
            }

            else -> Offset.Zero
        }
    }
}
