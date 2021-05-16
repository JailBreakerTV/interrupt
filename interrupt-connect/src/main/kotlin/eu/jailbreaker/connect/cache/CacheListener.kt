package eu.jailbreaker.connect.cache

import javax.cache.event.*

abstract class CacheListener<K, V> private constructor():
    CacheEntryUpdatedListener<K, V>,
    CacheEntryRemovedListener<K, V>,
    CacheEntryExpiredListener<K, V>,
    CacheEntryCreatedListener<K, V> {

    class Builder<K, V> {
        private var onUpdated: ((MutableIterable<CacheEntryEvent<out K, out V>>) -> Unit)? = null
        private var onRemoved: ((MutableIterable<CacheEntryEvent<out K, out V>>) -> Unit)? = null
        private var onExpired: ((MutableIterable<CacheEntryEvent<out K, out V>>) -> Unit)? = null
        private var onCreated: ((MutableIterable<CacheEntryEvent<out K, out V>>) -> Unit)? = null

        fun onUpdated(updated: ((MutableIterable<CacheEntryEvent<out K, out V>>) -> Unit)) {
            onUpdated = updated
        }

        fun onRemoved(removed: ((MutableIterable<CacheEntryEvent<out K, out V>>) -> Unit)) {
            onRemoved = removed
        }

        fun onExpired(expired: ((MutableIterable<CacheEntryEvent<out K, out V>>) -> Unit)) {
            onExpired = expired
        }

        fun onCreated(created: ((MutableIterable<CacheEntryEvent<out K, out V>>) -> Unit)) {
            onCreated = created
        }

        fun build(): CacheListener<K, V> {
            return object : CacheListener<K, V>() {
                override fun onUpdated(events: MutableIterable<CacheEntryEvent<out K, out V>>) {
                    onUpdated?.invoke(events)
                }

                override fun onRemoved(events: MutableIterable<CacheEntryEvent<out K, out V>>) {
                    onRemoved?.invoke(events)
                }

                override fun onExpired(events: MutableIterable<CacheEntryEvent<out K, out V>>) {
                    onExpired?.invoke(events)
                }

                override fun onCreated(events: MutableIterable<CacheEntryEvent<out K, out V>>) {
                    onCreated?.invoke(events)
                }
            }
        }
    }
}