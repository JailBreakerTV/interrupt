package eu.jailbreaker.connect

import org.apache.ignite.Ignite

interface IgniteAware {
    fun ignite(): Ignite
    fun start(): Boolean
    fun close() = ignite().close()
}