package eu.jailbreaker.plugin.bukkit.event

import eu.jailbreaker.connect.IgniteConnect
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class BukkitNodeStartedEvent(
    @JvmField
    val connect: IgniteConnect
) : Event() {
    override fun getHandlers(): HandlerList = HANDLERS

    companion object {
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList = HANDLERS
    }
}