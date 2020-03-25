import Hello.Companion.creditCardFormatted
import Hello.Companion.isEmailValid
import Hello.Companion.md5
import Hello.Companion.sha1
import java.lang.StringBuilder
import java.security.MessageDigest
import java.util.regex.Pattern

/**
 * Various String extension functions in Kotlin
 */
class Hello {
    companion object {
        val String.md5: String
            get() {
                val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
                return bytes.joinToString("") {
                    "%02x".format(it)
                }
            }

        val String.sha1: String
            get() {
                val bytes = MessageDigest.getInstance("SHA-1").digest(this.toByteArray())
                return bytes.joinToString("") {
                    "%02x".format(it)
                }
            }

        fun String.isEmailValid(): Boolean {
            val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(this)
            return matcher.matches()
        }

        val String.creditCardFormatted: String
            get() {
                val preparedString = replace(" ", "").trim()
                val result = StringBuilder()
                for (i in preparedString.indices) {
                    if (i % 4 == 0 && i != 0) {
                        result.append(" ")
                    }
                    result.append(preparedString[i])
                }
                return result.toString()
            }
    }
}

fun main() {
    println("Segun".md5)
    println("Segun".sha1)
    val email = "francissegun12@gmail.com"
    if (email.isEmailValid()) {
        println("Email is valid. Continue registration")
    } else {
        println("Email is not validate")
    }
    println("270748548927846502".creditCardFormatted)
}