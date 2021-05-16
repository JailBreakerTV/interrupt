package eu.jailbreaker.plugin.player.chat

import eu.jailbreaker.plugin.adapter.IgniteConnectAdapter
import eu.jailbreaker.plugin.adapter.UniqueIdAdapter

interface InterruptPlayerChatExtension : IgniteConnectAdapter, UniqueIdAdapter {
    fun chat(message: String) {}
    fun performCommand(command: String) = chat(command)
}