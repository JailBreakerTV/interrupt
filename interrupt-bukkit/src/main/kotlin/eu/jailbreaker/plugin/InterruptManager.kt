package eu.jailbreaker.plugin

import eu.jailbreaker.plugin.player.InterruptPlayer
import eu.jailbreaker.plugin.player.InterruptPlayerImpl
import java.util.*

object InterruptManager {
    fun findPlayer(uniqueId: UUID): InterruptPlayer = InterruptPlayerImpl(uniqueId)
    fun findPlayer(name: String): InterruptPlayer? =
        IgniteNames.CACHE_NAME_ID.get(name)?.let { InterruptPlayerImpl(it) }
}