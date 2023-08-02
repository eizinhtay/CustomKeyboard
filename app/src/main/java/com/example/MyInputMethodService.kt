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
    private var txt = ""
    private var faketxt = ""

    override fun onCreate() {
        super.onCreate()

    }
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
        if (inputConnection.getTextBeforeCursor(1, 0).toString() + "" == "") {
            txt = ""
            faketxt = ""
        }

        switchKeyboard(inputConnection, primaryCode)
        println("${inputConnection}*****")

        if (inputConnection != null) {
            when (primaryCode) {
                Keyboard.KEYCODE_DELETE -> {
                    faketxt=""
                    val selectedText = inputConnection.getSelectedText(0)
                    println("${selectedText}*****")

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
                    faketxt=""

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

                -22->{
                    faketxt=""

                    caps = !caps
                    keyboard!!.isShifted = caps
                    numberKeyboard=!numberKeyboard
                    shiftKeyboard =!shiftKeyboard
                    keyboardView!!.invalidateAllKeys()
                }
                -2->{
                    faketxt=""
                    caps = !caps
                    keyboard!!.isShifted = caps
                    numberKeyboard=!numberKeyboard
                    shiftKeyboard =!shiftKeyboard
                    keyboardView!!.invalidateAllKeys()
                }
                4174->{
                    faketxt =""

                    val txt="၎င်း"
                    val code = primaryCode.toChar()+"င်း"

                    inputConnection.commitText(code.toString(), 1)

                }


                -5000->{
                    faketxt=""

                    val text="ရ်္"
                    inputConnection.commitText(text.toString(), 1)

                }

                -5005->{
                    // Load the font from assets
                    faketxt =""

                    val text="ိဲ"
                    inputConnection.commitText(text, 1)

                }

                8217->{
                    faketxt =""
                    val code=8216
                    val code1 =4141
                    val txt = code.toChar()+"${ code1.toChar()}"
                    val text="❛ိ"
                    inputConnection.commitText(txt,1)
                }

                8216->{
                    faketxt =""

                    inputConnection.commitText(primaryCode.toChar().toString(), 1)


                }

                4145->{

                    println(txt+"txt:::")
                    println(faketxt+":::")

                    if (txt.isNotEmpty()){
                        val cha1Char = primaryCode.toChar()
                        val combination = "$faketxt$cha1Char"
                        println(combination+"combination:::")

                        inputConnection.commitText(combination,1)
                        txt=""
                    }else{
                        faketxt = primaryCode.toChar().toString()
                        inputConnection.commitText(faketxt,1)
                        txt=""
                    }


                }



                else -> {
                    if (faketxt.isNotEmpty()){
                       inputConnection.deleteSurroundingText(1, 0)

//                        if (txt.isEmpty()){
//                        }
                        val combination=combineCharacters(faketxt,primaryCode.toChar())
//                        val cha1Char = primaryCode.toChar()
//                        val combination ="$cha1Char "+faketxt
                        inputConnection.commitText(combination.toString(), 1)

                        faketxt =""
                    }else{

                        txt =primaryCode.toChar().toString()

                        inputConnection.commitText(txt, 1)
                        faketxt=""
                    }

                }
            }
        }
    }

    fun getMyanmarCharacter(firstKeyCode: Int, secondKeyCode: Int): Char? {
        // Unicode code point for the Myanmar characters starts from U+1000
        val baseCodePoint = 0x1000

        // Calculate the combined Unicode code point based on the key codes
        val combinedCodePoint = baseCodePoint + (firstKeyCode * 6) + secondKeyCode

        // Check if the combined code point is within the Myanmar Unicode range
        return if (combinedCodePoint in 0x1000..0x109F) {
            combinedCodePoint.toChar()
        } else {
            null // Return null for invalid combinations
        }
    }


    fun combineMyanmarCharacters(decimalCode1: Int, decimalCode2: Int): Char {
        // Convert decimal codes to integers
        val codePoint1 = decimalCode1.toInt()
        val codePoint2 = decimalCode2.toInt()

        // Combine the code points using bitwise operations
        val combinedCodePoint = (codePoint1 shl 16) + codePoint2
        println("Myanmar Character: $combinedCodePoint")


        // Convert the combined code point back to a character
        return combinedCodePoint.toChar()
    }

    fun combineCharacters(decimalCode1: String, decimalCode2: Char): String {

        // Convert decimal codes to integers
        val thawayHtoe = decimalCode1
        val text = decimalCode2.toChar()

        // Combine the code points using bitwise operations
        val combinedCodePoint = text+"$thawayHtoe"
        println("Myanmar Character: $combinedCodePoint")
        txt=""
        faketxt=""


        // Convert the combined code point back to a character
        return combinedCodePoint
    }
}