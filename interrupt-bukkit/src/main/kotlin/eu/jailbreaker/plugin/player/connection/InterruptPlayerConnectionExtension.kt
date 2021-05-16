package eu.jailbreaker.plugin.player.connection

import eu.jailbreaker.plugin.adapter.IgniteConnectAdapter
import eu.jailbreaker.plugin.adapter.UniqueIdAdapter
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer

interface InterruptPlayerConnectionExtension : IgniteConnectAdapter, UniqueIdAdapter {
    fun kick(reason: Component) = KickMessage(uniqueId(), GsonComponentSerializer.gson().serialize(reason)).send()
    fun connect(target: String) = ServerConnectMessage(uniqueId(), target).send()
}