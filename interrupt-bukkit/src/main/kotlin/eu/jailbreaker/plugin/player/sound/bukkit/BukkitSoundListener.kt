package eu.jailbreaker.plugin.player.sound.bukkit

import eu.jailbreaker.connect.message.MessageListener
import eu.jailbreaker.plugin.IgniteNames
import eu.jailbreaker.plugin.bukkit.audience
import eu.jailbreaker.plugin.player.sound.SoundMessage
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import org.bukkit.Bukkit

class BukkitSoundListener : MessageListener<SoundMessage> {
    override fun topic(): String = IgniteNames.TOPIC_USER_SOUND
    override fun id(): Int = BukkitSoundListener::class.java.name.hashCode()

    override fun onMessageReceived(message: SoundMessage) {
        Bukkit.getPlayer(message.uniqueId)?.let { player ->
            audience.player(player).playSound(
                Sound.sound(
                    Key.key(message.name),
                    Sound.Source.valueOf(message.source.toUpperCase()),
                    message.volume,
                    message.pitch
                )
            )
        }
    }
}