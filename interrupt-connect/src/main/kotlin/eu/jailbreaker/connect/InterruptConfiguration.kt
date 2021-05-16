package eu.jailbreaker.connect

import java.util.*

data class InterruptConfiguration(
    val client: Boolean = true,
    val peerClassLoading: Boolean = true,
    val addresses: Collection<String> = Collections.singletonList("127.0.0.1:47500..47509"),
)