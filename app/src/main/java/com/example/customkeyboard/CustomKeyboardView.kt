package com.example.customkeyboard


import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.util.AttributeSet

class CustomKeyboardView : KeyboardView {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    // Override this method to apply custom font style
//    override fun onDraw(canvas: Canvas) {
//        val paint = Paint()
//        paint.typeface = Typeface.createFromAsset(context.assets, "taung_yoe.ttf")
//        super.onDraw(canvas)
//    }
}
