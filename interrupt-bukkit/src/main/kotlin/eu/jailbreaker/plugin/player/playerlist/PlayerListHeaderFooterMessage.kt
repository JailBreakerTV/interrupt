package eu.jailbreaker.plugin.player.playerlist

import eu.jailbreaker.connect.message.UniqueIdMessage
import eu.jailbreaker.plugin.IgniteNames
import java.util.*

class PlayerListHeaderFooterMessage(
    uuid: UUID,
    @JvmField
    val header: String?,
    @JvmField
    val footer: String?
) : UniqueIdMessage(uuid, IgniteNames.TOPIC_PLAYER_LIST_HEADER_FOOTER)