package eu.jailbreaker.plugin.player.message.bukkit

import eu.jailbreaker.connect.message.MessageListener
import eu.jailbreaker.plugin.IgniteNames
import eu.jailbreaker.plugin.bukkit.audience
import eu.jailbreaker.plugin.player.message.TitleModifyMessage
import org.bukkit.Bukkit

class BukkitTitleModifyListener : MessageListener<TitleModifyMessage> {
    override fun topic(): String = IgniteNames.TOPIC_USER_TITLE_MODIFY
    override fun id(): Int = BukkitTitleModifyListener::class.java.name.hashCode()

    override fun onMessageReceived(message: TitleModifyMessage) {
        Bukkit.getPlayer(message.uniqueId)?.let { player ->
            when (TitleModifyMessage.TitleModifyAction.findById(message.action)) {
                TitleModifyMessage.TitleModifyAction.CLEAR -> audience.player(player).clearTitle()
                TitleModifyMessage.TitleModifyAction.RESET -> audience.player(player).resetTitle()
                else -> {
                }
            }
        }
    }
}