package eu.jailbreaker.plugin.player.playerlist

import eu.jailbreaker.plugin.adapter.IgniteConnectAdapter
import eu.jailbreaker.plugin.adapter.UniqueIdAdapter
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer

interface InterruptPlayerListExtension : IgniteConnectAdapter, UniqueIdAdapter {
    fun playerListHeader(header: Component) = PlayerListHeaderFooterMessage(
        uniqueId(),
        GsonComponentSerializer.gson().serialize(header),
        null
    ).send()

    fun playerListFooter(footer: Component) = PlayerListHeaderFooterMessage(
        uniqueId(),
        null,
        GsonComponentSerializer.gson().serialize(footer)
    ).send()

    fun playerListHeaderFooter(header: Component, footer: Component) = PlayerListHeaderFooterMessage(
        uniqueId(),
        GsonComponentSerializer.gson().serialize(header),
        GsonComponentSerializer.gson().serialize(footer)
    ).send()
}