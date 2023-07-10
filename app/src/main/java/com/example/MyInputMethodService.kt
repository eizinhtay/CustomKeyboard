package com.example

import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputConnection
import com.example.customkeyboard.R


class MyInputMethodService: InputMethodService(),KeyboardView.OnKeyboardActionListener {

    private var keyboardView: KeyboardView? = null
    private var keyboard: Keyboard? = null

    private var caps = false
    private var shiftKeyboard = false
    private var numberKeyboard = false


    override fun onCreateInputView(): View? {
        keyboardView = layoutInflater.inflate(R.layout.keyboard_view, null) as KeyboardView?
        keyboard = Keyboard(this, R.xml.mm_unicode)
        keyboardView!!.keyboard = keyboard
        keyboardView!!.setOnKeyboardActionListener(this)
        return keyboardView
    }
    private fun switchKeyboard(inputConnection: InputConnection, i: Int) {
        Log.d("DEBUG", "I: $i")

        Log.d("middle", "switchKeyboard: before switch")
        when (i) {
            -11 -> {
                keyboard = Keyboard(this, R.xml.mm_unicode)
                keyboardView!!.keyboard = keyboard
                keyboardView!!.setOnKeyboardActionListener(this)
            }

            -1 -> {
                shiftKeyboard=true
                keyboard = Keyboard(this, R.xml.mm_unicode_shift)
                keyboardView!!.keyboard = keyboard
                keyboardView!!.setOnKeyboardActionListener(this)
            }

            -22 -> {
                shiftKeyboard=true
                keyboard = Keyboard(this, R.xml.mm_unicode_two)
                keyboardView!!.keyboard = keyboard
                keyboardView!!.setOnKeyboardActionListener(this)
            }
            else->{
                keyboard = Keyboard(this, R.xml.mm_unicode)
                keyboardView!!.keyboard = keyboard
                keyboardView!!.setOnKeyboardActionListener(this)
            }
        }

        if (shiftKeyboard){
            when(i){
                -2 ->{
                    numberKeyboard = true
                    keyboard = Keyboard(this, R.xml.mm_unicode_two)
                    keyboardView!!.keyboard = keyboard
                    keyboardView!!.setOnKeyboardActionListener(this)
                }
            }
        }

        if (numberKeyboard){
            when(i){
                -2 ->{
                    numberKeyboard = true
                    keyboard = Keyboard(this, R.xml.mm_unicode)
                    keyboardView!!.keyboard = keyboard
                    keyboardView!!.setOnKeyboardActionListener(this)
                }
            }
        }
    }

//    override fun switchInputMethod(id: String?) {
//        super.switchInputMethod(id)
//    }

    override fun onPress(primaryCode: Int) {

    }

    override fun onRelease(primaryCode: Int) {

    }

    override fun onText(text: CharSequence?) {

    }

    override fun swipeLeft() {

    }

    override fun swipeRight() {

    }

    override fun swipeDown() {

    }

    override fun swipeUp() {

    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val inputConnection = currentInputConnection
        switchKeyboard(inputConnection, primaryCode)

        if (inputConnection != null) {
            when (primaryCode) {
                Keyboard.KEYCODE_DELETE -> {

                    val selectedText = inputConnection.getSelectedText(0)
                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(1, 0)
                    } else {
                        inputConnection.commitText("", 1)
                    }
                    caps = !caps
                    shiftKeyboard =!shiftKeyboard
                    numberKeyboard=!numberKeyboard
                    keyboard!!.isShifted = caps
                    keyboardView!!.invalidateAllKeys()
                }

                Keyboard.KEYCODE_SHIFT -> {
                    caps = !caps
                    keyboard!!.isShifted = caps
                    numberKeyboard=!numberKeyboard
                    shiftKeyboard =!shiftKeyboard
                    keyboardView!!.invalidateAllKeys()
                }

                Keyboard.KEYCODE_DONE -> inputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_ENTER
                    )
                )

                else -> {
                    var code = primaryCode.toChar()
                    if (Character.isLetter(code) && caps) {
                        code = code.uppercaseChar()
                    }
                    inputConnection.commitText(code.toString(), 1)
                }
            }
        }
    }

    private fun handleUni(primaryCode: Int) {


    }

}