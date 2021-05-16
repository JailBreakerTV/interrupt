package eu.jailbreaker.plugin.player

import java.util.*

class InterruptPlayerImpl(
    @JvmField
    val uniqueId: UUID
) : InterruptPlayer {
    override fun uniqueId(): UUID = uniqueId
}