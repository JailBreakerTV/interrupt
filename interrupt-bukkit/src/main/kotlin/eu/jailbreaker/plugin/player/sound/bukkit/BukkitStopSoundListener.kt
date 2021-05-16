package eu.jailbreaker.plugin.player.sound.bukkit

import eu.jailbreaker.connect.message.MessageListener
import eu.jailbreaker.plugin.IgniteNames
import eu.jailbreaker.plugin.bukkit.audience
import eu.jailbreaker.plugin.player.sound.StopSoundMessage
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.SoundStop
import org.bukkit.Bukkit

class BukkitStopSoundListener : MessageListener<StopSoundMessage> {
    override fun id(): Int = BukkitStopSoundListener::class.java.name.hashCode()
    override fun topic(): String = IgniteNames.TOPIC_USER_STOP_SOUND

    override fun onMessageReceived(message: StopSoundMessage) {
        Bukkit.getPlayer(message.uniqueId)?.let { player ->
            message.key?.let {
                audience.player(player).stopSound(SoundStop.named(Key.key(it)))
            }
        }
    }
}