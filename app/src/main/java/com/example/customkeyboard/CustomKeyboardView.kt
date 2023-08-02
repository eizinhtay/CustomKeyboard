///*
//* Copyright (C) 2008-2009 Google Inc.
//*
//* Licensed under the Apache License, Version 2.0 (the "License"); you may not
//* use this file except in compliance with the License. You may obtain a copy of
//* the License at
//*
//* http://www.apache.org/licenses/LICENSE-2.0
//*
//* Unless required by applicable law or agreed to in writing, software
//* distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
//* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
//* License for the specific language governing permissions and limitations under
//* the License.
//*/
//package com.example.customkeyboard
//import android.content.Context
//import android.content.SharedPreferences
//import android.content.res.Resources
//import android.graphics.Canvas
//import android.graphics.Paint
//import android.graphics.Paint.Align
//import android.graphics.drawable.Drawable
//import android.inputmethodservice.Keyboard
//import android.inputmethodservice.KeyboardView
//import android.preference.PreferenceManager
//import android.util.AttributeSet
//
///**
// * A view that renders a virtual [Keyboard]. It handles rendering of keys and
// * detecting key presses and touch movements.
// *
// * @attr ref android.R.styleable#KeyboardView_keyBackground
// * @attr ref android.R.styleable#KeyboardView_keyPreviewLayout
// * @attr ref android.R.styleable#KeyboardView_keyPreviewOffset
// * @attr ref android.R.styleable#KeyboardView_keyPreviewHeight
// * @attr ref android.R.styleable#KeyboardView_labelTextSize
// * @attr ref android.R.styleable#KeyboardView_keyTextSize
// * @attr ref android.R.styleable#KeyboardView_keyTextColor
// * @attr ref android.R.styleable#KeyboardView_verticalCorrection
// * @attr ref android.R.styleable#KeyboardView_popupLayout
// *
// */
//class CustomKeyboard(context: Context, attrs: AttributeSet) :
//    KeyboardView(context, attrs) {
//    var context: Context=context
//   // var attrs: AttributeSet = attrs
//    var settings: SharedPreferences? = null
//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//        var dr: Drawable
//        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
//        paint.textAlign = Align.CENTER
//        val keys = keyboard.keys
//        settings = PreferenceManager.getDefaultSharedPreferences(context)
//        val currentTheme = settings!!.getInt("theme", 0)
//        if (currentTheme < 2 || isEmoji) {
//            paint.textSize = sptopx(23f, context)
//            for (key in keys) {
//                if (key.codes[0] == 32) {
//                    dr = context.resources.getDrawable(R.drawable.ic_up)
//                    dr.setBounds(
//                        key.x + dpToPx(6),
//                        key.y + dpToPx(6),
//                        key.x + key.width - dpToPx(6),
//                        key.y + key.height - dpToPx(6)
//                    )
//                    dr.draw(canvas!!)
//                } else if (key.codes[0] == -4) {
//                    val width = key.width
//                    val height = key.height
//                    val left = key.x + width / 2 - height / 2
//                    val top = key.y
//                    val right = left + height
//                    val bottom = top + height
//                    var padding = dpToPx(5)
//                    dr = context.resources.getDrawable(R.drawable.ic_up)
//                    dr.setBounds(left + padding, top + padding, right - padding, bottom - padding)
//                    dr.draw(canvas!!)
//                    padding = dpToPx(6)
//                    key.icon.setBounds(
//                        left + padding,
//                        top + padding,
//                        right - padding,
//                        bottom - padding
//                    )
//                    key.icon.draw(canvas)
//                }
//            }
//        } else {
//            for (key in keys) if (key.codes[0] == -4) {
//                val width = key.width
//                val height = key.height
//                val left = key.x + width / 2 - height / 2
//                val top = key.y
//                val right = left + height
//                val bottom = top + height
//                dr = context.resources.getDrawable(R.drawable.ic_up)
//                var padding = 0
//                if (settings!!.getBoolean("height", false)) {
//                    padding = dpToPx(10)
//                }
//                dr.setBounds(
//                    key.x + padding,
//                    key.y + padding,
//                    key.x + width - padding,
//                    key.y + height - padding
//                )
//                dr.draw(canvas!!)
//                padding += dpToPx(6)
//                key.icon.setBounds(left + padding, top + padding, right - padding, bottom - padding)
//                key.icon.draw(canvas)
//            }
//        }
//    }
//
//    private var isEmoji = false
//
//    init {
//        this.context = context
//       // this.attrs = attrs
//    }
//
//
//
//    fun setKeyboard(keyboard: Keyboard, b: Boolean) {
//        setKeyboard(keyboard)
//    }
//
//    companion object {
//        fun dpToPx(dp: Int): Int {
//            return (dp * Resources.getSystem().displayMetrics.density).toInt()
//        }
//
//        fun pxToDp(px: Int): Int {
//            return (px / Resources.getSystem().displayMetrics.density).toInt()
//        }
//
//        fun sptopx(sp: Float, context: Context): Float {
//            val scaledDensity = context.resources.displayMetrics.scaledDensity
//            return sp * scaledDensity
//        }
//    }
//}