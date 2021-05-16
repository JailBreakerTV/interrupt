package eu.jailbreaker.plugin.player.message

import eu.jailbreaker.connect.message.UniqueIdMessage
import eu.jailbreaker.plugin.IgniteNames
import java.util.*

class ActionBarMessage(
    uuid: UUID,
    @JvmField
    val actionBar: String
) : UniqueIdMessage(uuid, IgniteNames.TOPIC_USER_ACTIONBAR)