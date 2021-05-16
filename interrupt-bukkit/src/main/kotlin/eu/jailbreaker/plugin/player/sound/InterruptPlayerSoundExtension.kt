package eu.jailbreaker.plugin.player.sound

import eu.jailbreaker.plugin.adapter.IgniteConnectAdapter
import eu.jailbreaker.plugin.adapter.UniqueIdAdapter
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.sound.SoundStop

interface InterruptPlayerSoundExtension : IgniteConnectAdapter, UniqueIdAdapter {
    fun stopSound(soundStop: SoundStop) = StopSoundMessage(uniqueId(), soundStop).send()
    fun playSound(sound: Sound) = SoundMessage(uniqueId(), sound).send()
}