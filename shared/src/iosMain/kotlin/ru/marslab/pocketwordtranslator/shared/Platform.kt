package ru.marslab.pocketwordtranslator.shared

actual class Platform actual constructor() {
    actual val platform: String = "" // TODO Release in ios
//        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}
