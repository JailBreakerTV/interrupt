package eu.jailbreaker.plugin.player.message

import eu.jailbreaker.connect.message.UniqueIdMessage
import eu.jailbreaker.plugin.IgniteNames
import java.util.*

class TitleMessage(
    uuid: UUID,
    @JvmField
    val title: String,
    @JvmField
    val subtitle: String,
    @JvmField
    val fadeId: Long,
    @JvmField
    val stay: Long,
    @JvmField
    val fadeOut: Long
) : UniqueIdMessage(uuid, IgniteNames.TOPIC_USER_TITLE)