package eu.jailbreaker.connect

import org.apache.ignite.*
import org.apache.ignite.cache.affinity.Affinity
import org.apache.ignite.cluster.ClusterGroup
import org.apache.ignite.cluster.ClusterMetrics
import org.apache.ignite.cluster.ClusterState
import org.apache.ignite.configuration.AtomicConfiguration
import org.apache.ignite.configuration.CacheConfiguration
import org.apache.ignite.configuration.CollectionConfiguration
import org.apache.ignite.configuration.NearCacheConfiguration
import org.apache.ignite.lang.IgniteProductVersion
import java.util.concurrent.ExecutorService

interface IgniteConnect : IgniteAware, IgniteConfigurationAware {
    fun name(): String = ignite().name()
    fun logger(): IgniteLogger = ignite().log()
    fun cluster(): IgniteCluster = ignite().cluster()
    fun clusterState(): ClusterState = cluster().state()
    fun clusterMetrics(): ClusterMetrics = cluster().metrics()
    fun compute(): IgniteCompute = ignite().compute()
    fun compute(group: ClusterGroup): IgniteCompute = ignite().compute(group)
    fun messaging(): IgniteMessaging = ignite().message()
    fun messaging(group: ClusterGroup): IgniteMessaging = ignite().message(group)
    fun events(): IgniteEvents = ignite().events()
    fun events(group: ClusterGroup): IgniteEvents = ignite().events(group)
    fun services(): IgniteServices = ignite().services()
    fun services(group: ClusterGroup): IgniteServices = ignite().services(group)
    fun executorService(): ExecutorService = ignite().executorService()
    fun executorService(group: ClusterGroup): ExecutorService = ignite().executorService(group)
    fun version(): IgniteProductVersion = ignite().version()
    fun scheduler(): IgniteScheduler = ignite().scheduler()

    fun <K, V> addCacheConfigurationTemplate(conf: CacheConfiguration<K, V>) = ignite().addCacheConfiguration(conf)

    fun <K, V> cache(name: String): IgniteCache<K, V> = ignite().getOrCreateCache(name)
    fun <K, V> cache(conf: CacheConfiguration<K, V>): IgniteCache<K, V> = ignite().getOrCreateCache(conf)
    fun <K, V> nearCache(name: String, conf: NearCacheConfiguration<K, V>): IgniteCache<K, V> =
        ignite().getOrCreateNearCache(name, conf)

    fun <K, V> createCache(name: String): IgniteCache<K, V> = ignite().createCache(name)
    fun <K, V> createCache(conf: CacheConfiguration<K, V>): IgniteCache<K, V> = ignite().createCache(conf)
    fun destroyCache(name: String) = ignite().destroyCache(name)
    fun destroyCaches(names: Collection<String>) = ignite().destroyCaches(names)
    fun caches(): Collection<String> = ignite().cacheNames()

    fun transactions(): IgniteTransactions = ignite().transactions()
    fun <K, V> dataStreamer(cache: String): IgniteDataStreamer<K, V> = ignite().dataStreamer(cache)
    fun <T> atomicReference(name: String, default: T?, create: Boolean): IgniteAtomicReference<T> =
        ignite().atomicReference(name, default, create)

    fun <T> atomicReference(
        name: String,
        conf: AtomicConfiguration,
        default: T?,
        create: Boolean
    ): IgniteAtomicReference<T> =
        ignite().atomicReference(name, conf, default, create)

    fun reentrantLock(name: String, failoverSafe: Boolean, fair: Boolean, create: Boolean): IgniteLock =
        ignite().reentrantLock(name, failoverSafe, fair, create)

    fun <T> queue(name: String): IgniteQueue<T> = queue(name, 0, null)
    fun <T> queue(name: String, conf: CollectionConfiguration): IgniteQueue<T> = queue(name, 0, conf)
    fun <T> queue(name: String, capacity: Int, conf: CollectionConfiguration?): IgniteQueue<T> =
        ignite().queue(name, capacity, conf)

    fun <T> set(name: String): IgniteSet<T> = set(name, null)
    fun <T> set(name: String, conf: CollectionConfiguration?): IgniteSet<T> = ignite().set(name, conf)

    fun binary(): IgniteBinary = ignite().binary()
    fun <K> affinity(cache: String): Affinity<K> = ignite().affinity(cache)

    fun encryption(): IgniteEncryption = ignite().encryption()
    fun snapshot(): IgniteSnapshot = ignite().snapshot()

    companion object {
        private val connectImpl: IgniteConnect = IgniteConnectImpl()
        fun getInstance(): IgniteConnect = connectImpl
    }
}