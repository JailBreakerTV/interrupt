package eu.jailbreaker.connect.message

import java.util.*

open class UniqueIdMessage(val uniqueId: UUID, topic: String) : InterruptMessage(topic)