package eu.jailbreaker.plugin.player

import eu.jailbreaker.connect.IgniteConnect
import eu.jailbreaker.plugin.IgniteNames
import eu.jailbreaker.plugin.adapter.NameAdapter
import eu.jailbreaker.plugin.adapter.UniqueIdAdapter
import eu.jailbreaker.plugin.player.chat.InterruptPlayerChatExtension
import eu.jailbreaker.plugin.player.connection.InterruptPlayerConnectionExtension
import eu.jailbreaker.plugin.player.message.InterruptPlayerMessageExtension
import eu.jailbreaker.plugin.player.playerlist.InterruptPlayerListExtension
import eu.jailbreaker.plugin.player.sound.InterruptPlayerSoundExtension

interface InterruptPlayer : UniqueIdAdapter, NameAdapter,
    InterruptPlayerChatExtension,
    InterruptPlayerConnectionExtension,
    InterruptPlayerMessageExtension,
    InterruptPlayerListExtension,
    InterruptPlayerSoundExtension {

    override fun igniteConnect(): IgniteConnect = IgniteConnect.getInstance()
    override fun name(): String? = IgniteNames.CACHE_ID_NAME.get(uniqueId())
    override fun displayName(): String? = IgniteNames.CACHE_ID_DISPLAY_NAME.get(uniqueId())
    override fun displayName(displayName: String) = IgniteNames.CACHE_ID_DISPLAY_NAME.put(uniqueId(), displayName)
}