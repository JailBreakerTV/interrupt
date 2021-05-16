package eu.jailbreaker.plugin.player.connection

import eu.jailbreaker.connect.message.UniqueIdMessage
import eu.jailbreaker.plugin.IgniteNames
import java.util.*

class ServerConnectMessage(uuid: UUID, val target: String) : UniqueIdMessage(uuid, IgniteNames.TOPIC_USER_CONNECT)