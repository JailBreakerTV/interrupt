package eu.jailbreaker.plugin.bukkit.event

import org.apache.ignite.configuration.IgniteConfiguration
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class BukkitNodePreStartEvent(
    @JvmField
    val igniteConfiguration: IgniteConfiguration
    ) : Event() {
    override fun getHandlers(): HandlerList = HANDLERS

    companion object {
        private val HANDLERS = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList = HANDLERS
    }
}