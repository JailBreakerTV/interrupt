package eu.jailbreaker.plugin.player.sound

import eu.jailbreaker.connect.message.UniqueIdMessage
import eu.jailbreaker.plugin.IgniteNames
import net.kyori.adventure.sound.Sound
import java.util.*

class SoundMessage(
    uuid: UUID,
    @JvmField
    val name: String,
    @JvmField
    val source: String,
    @JvmField
    val volume: Float,
    @JvmField
    val pitch: Float
) : UniqueIdMessage(uuid, IgniteNames.TOPIC_USER_SOUND) {
    constructor(uuid: UUID, sound: Sound) : this(
        uuid,
        sound.name().asString(),
        sound.source().name,
        sound.volume(),
        sound.pitch()
    )
}