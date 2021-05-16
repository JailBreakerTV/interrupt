package eu.jailbreaker.plugin.adapter

interface NameAdapter {
    fun name(): String?
    fun displayName(): String?
    fun displayName(displayName: String)
}