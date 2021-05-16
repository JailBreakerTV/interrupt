package eu.jailbreaker.plugin.player.sound

import eu.jailbreaker.connect.message.UniqueIdMessage
import eu.jailbreaker.plugin.IgniteNames
import net.kyori.adventure.sound.SoundStop
import java.util.*

class StopSoundMessage(
    uuid: UUID,
    @JvmField
    val key: String?,
) : UniqueIdMessage(uuid, IgniteNames.TOPIC_USER_STOP_SOUND) {
    constructor(uuid: UUID, soundStop: SoundStop) : this(uuid, soundStop.sound()?.asString())
}