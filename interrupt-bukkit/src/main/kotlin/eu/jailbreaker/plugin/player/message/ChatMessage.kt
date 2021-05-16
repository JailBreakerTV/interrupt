package eu.jailbreaker.plugin.player.message

import eu.jailbreaker.connect.message.UniqueIdMessage
import eu.jailbreaker.plugin.IgniteNames
import java.util.*

class ChatMessage(
    uuid: UUID,
    @JvmField
    val message: String
) : UniqueIdMessage(uuid, IgniteNames.TOPIC_USER_CHAT)