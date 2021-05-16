package eu.jailbreaker.connect.message

import eu.jailbreaker.connect.IgniteConnect
import org.apache.ignite.lang.IgniteBiPredicate
import java.util.*
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.collections.ArrayList

interface MessageListener<M> : IgniteBiPredicate<UUID, M> {
    fun id(): Int
    fun topic(): String
    fun onMessageReceived(message: M) {}
    fun onMessageReceived(nodeId: UUID, message: M) {}

    @Suppress("UNCHECKED_CAST")
    fun register() {
        registerListener(this as MessageListener<Any>)
        IgniteConnect.getInstance().messaging().localListen(topic(), this)
    }

    fun unregister() {
        unregisterListener(id())
        IgniteConnect.getInstance().messaging().stopLocalListen(topic(), this)
    }

    override fun apply(nodeId: UUID, message: M): Boolean {
        onMessageReceived(nodeId, message)
        onMessageReceived(message)
        return true
    }

    companion object {
        private val lock = ReentrantReadWriteLock()
        private val registeredListeners = mutableMapOf<Int, MessageListener<Any>>()

        fun registerListener(listener: MessageListener<Any>) {
            lock.writeLock().lock()
            registeredListeners[listener.id()] = listener
            lock.writeLock().unlock()
        }

        fun unregisterListener(id: Int) {
            lock.writeLock().lock()
            registeredListeners.remove(id)
            lock.writeLock().unlock()
        }

        fun unregisterListener(listener: MessageListener<Any>) = unregisterListener(listener.id())

        fun registeredListeners(): Collection<MessageListener<Any>> = ArrayList(registeredListeners.values)
    }
}