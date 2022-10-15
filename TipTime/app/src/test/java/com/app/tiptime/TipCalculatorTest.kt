package com.app.tiptime

import junit.framework.Assert.assertEquals
import org.junit.Test

class TipCalculatorTest {

    @Test
    fun  calculate_20_percent_Tip_no_roundup(){
        val amount=10.00
        val tip=20.00
        val expectedTip="$2.00"
        val actualTip= calculateTip(amount,tip,true)
        assertEquals(expectedTip,actualTip)
    }
}