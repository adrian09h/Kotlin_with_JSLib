package com.talkingmedicines.android.extensions

//import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun String.md5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.joinToString("") {
        "%02x".format(it)
    }
}

fun String.sha1(): String{
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

//fun String.formatPhoneNumber(region: String): String? {
//    val phoneNumberKit = PhoneNumberUtil.getInstance()
//    val number = phoneNumberKit.parse(this, region)
//    if (!phoneNumberKit.isValidNumber(number))
//        return null
//
//    return phoneNumberKit.format(number, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
//}

fun String.getRandomUUID(): String {
    var uuid = UUID.randomUUID().toString()
    return uuid
}

fun String.convertToDate(): Date? {
    val simpleDateFormat = SimpleDateFormat("dd/MMM/yyyy HH:mm")
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return simpleDateFormat.parse(this)
}