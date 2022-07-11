package com.kvertinum01

import com.kvertinum01.database.factory.DatabaseFactory
import com.kvertinum01.plugins.configureMonitoring
import com.kvertinum01.plugins.configureRouting
import com.kvertinum01.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    DatabaseFactory.init()
    configureRouting()
    configureMonitoring()
    configureSerialization()
}
