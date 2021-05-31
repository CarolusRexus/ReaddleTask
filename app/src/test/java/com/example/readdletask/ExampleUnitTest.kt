package com.example.readdletask

import org.junit.Test
import com.example.readdletask.util.md5
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun hash_isCorrect() {
        assertEquals("f9879d71855b5ff21e4963273a886bfc", "MyEmailAddress@example.com ".md5)
    }
}