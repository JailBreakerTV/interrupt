package eu.jailbreaker.plugin.player.playerlist

import eu.jailbreaker.connect.message.MessageListener
import eu.jailbreaker.plugin.IgniteNames
import eu.jailbreaker.plugin.bukkit.audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import org.bukkit.Bukkit

class BukkitPlayerListHeaderFooterListener : MessageListener<PlayerListHeaderFooterMessage> {
    override fun id(): Int = BukkitPlayerListHeaderFooterListener::class.java.hashCode()
    override fun topic(): String = IgniteNames.TOPIC_PLAYER_LIST_HEADER_FOOTER

    override fun onMessageReceived(message: PlayerListHeaderFooterMessage) {
        Bukkit.getPlayer(message.uniqueId)?.let { player ->
            val gson = GsonComponentSerializer.gson()
            val header = message.header?.let { gson.deserialize(it) } ?: Component.empty()
            val footer = message.footer?.let { gson.deserialize(it) } ?: Component.empty()
            audience.player(player).sendPlayerListHeaderAndFooter(header, footer)
        }
    }
}