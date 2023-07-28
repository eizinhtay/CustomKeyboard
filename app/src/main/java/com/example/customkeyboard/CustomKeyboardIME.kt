package com.example.customkeyboard
import android.graphics.Typeface
import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener
import android.view.KeyEvent
import android.view.View
import android.widget.TextView


class CustomKeyboardIME : InputMethodService(), OnKeyboardActionListener {
    private var keyboardView: KeyboardView? = null
    private var keyboard: Keyboard? = null
    override fun onCreateInputView(): View {
        keyboardView = layoutInflater.inflate(R.layout.custom_keyboard, null) as KeyboardView?
        keyboard = Keyboard(this, R.xml.mm_unicode)
        keyboardView!!.keyboard = keyboard
        keyboardView!!.setOnKeyboardActionListener(this)
        return keyboardView!!
    }

    override fun onPress(primaryCode: Int) {
        // Key press handling logic
    }

    override fun onRelease(primaryCode: Int) {
        // Key release handling logic
    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray) {
        val inputConnection = currentInputConnection

        if (inputConnection != null) {
            when (primaryCode) {
                Keyboard.KEYCODE_DELETE -> {}

                KeyEvent.KEYCODE_A -> inputConnection.commitText("A", 1)
                KeyEvent.KEYCODE_B -> inputConnection.commitText("B", 1)

                -5005->{
                    // Load the font from assets
                    val textV=TextView(this)
                    val text=primaryCode.toChar()


                    inputConnection.commitText(text.toString(), 1)

                }
                2020->{
                    // Load the font from assets
                    val textV=TextView(this)
                    val text= primaryCode.toChar()
                    inputConnection.commitText(text.toString(), 1)

                }
                else -> {
                    val code = primaryCode.toChar()
                    inputConnection.commitText(code.toString(), 1)
                }
            }
        }
    } // Implement other methods of the KeyboardView.OnKeyboardActionListener interface if needed

    override fun onText(text: CharSequence?) {

    }

    override fun swipeLeft() {
        TODO("Not yet implemented")
    }

    override fun swipeRight() {
        TODO("Not yet implemented")
    }

    override fun swipeDown() {
        TODO("Not yet implemented")
    }

    override fun swipeUp() {
        TODO("Not yet implemented")
    }
}
