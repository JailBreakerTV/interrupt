package eu.jailbreaker.plugin.player.message.bukkit

import eu.jailbreaker.connect.message.MessageListener
import eu.jailbreaker.plugin.IgniteNames
import eu.jailbreaker.plugin.bukkit.audience
import eu.jailbreaker.plugin.player.message.TitleMessage
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import java.time.Duration

class BukkitTitleListener : MessageListener<TitleMessage> {
    override fun topic(): String = IgniteNames.TOPIC_USER_TITLE
    override fun id(): Int = BukkitTitleListener::class.java.name.hashCode()

    override fun onMessageReceived(message: TitleMessage) {
        Bukkit.getPlayer(message.uniqueId)?.let { player ->
            val gson = GsonComponentSerializer.gson()
            val title = gson.deserialize(message.title)
            val subtitle = gson.deserialize(message.subtitle)
            audience.player(player).showTitle(
                Title.title(
                    title,
                    subtitle,
                    Title.Times.of(
                        Duration.ofMillis(message.fadeId),
                        Duration.ofMillis(message.stay),
                        Duration.ofMillis(message.fadeOut)
                    )
                )
            )
        }
    }
}