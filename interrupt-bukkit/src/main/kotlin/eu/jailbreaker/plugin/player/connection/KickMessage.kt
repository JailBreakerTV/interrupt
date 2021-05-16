package eu.jailbreaker.plugin.player.connection

import eu.jailbreaker.connect.message.UniqueIdMessage
import eu.jailbreaker.plugin.IgniteNames
import java.util.*

class KickMessage(uuid: UUID, val reason: String) : UniqueIdMessage(uuid, IgniteNames.TOPIC_USER_KICK)