package eu.jailbreaker.plugin.player.message.bukkit

import eu.jailbreaker.connect.message.MessageListener
import eu.jailbreaker.plugin.IgniteNames
import eu.jailbreaker.plugin.bukkit.audience
import eu.jailbreaker.plugin.player.message.ActionBarMessage
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import org.bukkit.Bukkit

class BukkitActionBarListener : MessageListener<ActionBarMessage> {
    override fun topic(): String = IgniteNames.TOPIC_USER_ACTIONBAR
    override fun id(): Int = BukkitActionBarListener::class.java.name.hashCode()

    override fun onMessageReceived(message: ActionBarMessage) {
        Bukkit.getPlayer(message.uniqueId)?.let { player ->
            val component = GsonComponentSerializer.gson().deserialize(message.actionBar)
            audience.player(player).sendActionBar(component)
        }
    }
}