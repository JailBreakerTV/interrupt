package eu.jailbreaker.connect.message

import eu.jailbreaker.connect.IgniteConnect

open class InterruptMessage(val topic: String) {
    fun send() = IgniteConnect.getInstance().messaging().send(topic, this)
}