package com.dx777.yasirnramaya.aburafelsystem

import com.chibatching.kotpref.KotprefModel

object Consts {
    const val BASE_URL = "https://aborafel.herokuapp.com/api/user/"
}

object cacheObj : KotprefModel() {
    var tokenCache by stringPref(default = "")
}