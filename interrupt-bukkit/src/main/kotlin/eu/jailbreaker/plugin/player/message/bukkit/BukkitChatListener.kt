package eu.jailbreaker.plugin.player.message.bukkit

import eu.jailbreaker.connect.message.MessageListener
import eu.jailbreaker.plugin.IgniteNames
import eu.jailbreaker.plugin.bukkit.audience
import eu.jailbreaker.plugin.player.message.ChatMessage
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import org.bukkit.Bukkit

class BukkitChatListener : MessageListener<ChatMessage> {
    override fun topic(): String = IgniteNames.TOPIC_USER_CHAT
    override fun id(): Int = BukkitChatListener::class.java.name.hashCode()

    override fun onMessageReceived(message: ChatMessage) {
        Bukkit.getPlayer(message.uniqueId)?.let { player ->
            val component = GsonComponentSerializer.gson().deserialize(message.message)
            audience.player(player).sendMessage(component)
        }
    }
}