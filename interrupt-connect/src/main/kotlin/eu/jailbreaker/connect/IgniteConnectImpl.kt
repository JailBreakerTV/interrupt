package eu.jailbreaker.connect

import eu.jailbreaker.connect.cache.CacheListener
import org.apache.ignite.Ignite
import org.apache.ignite.Ignition
import org.apache.ignite.configuration.IgniteConfiguration
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder
import java.util.*

class IgniteConnectImpl : IgniteConnect {
    private lateinit var ignite: Ignite
    private var configuration: IgniteConfiguration = createConfiguration()

    private fun createConfiguration(): IgniteConfiguration {
        // Preparing IgniteConfiguration using Java APIs
        val igniteConfiguration = IgniteConfiguration()

        // The node will be started as a client node.
        igniteConfiguration.isClientMode = true

        // Classes of custom Java logic will be transferred over the wire from this app.
        igniteConfiguration.isPeerClassLoadingEnabled = true

        // Setting up an IP Finder to ensure the client can locate the servers.
        val ipFinder = TcpDiscoveryMulticastIpFinder()
        ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"))
        igniteConfiguration.discoverySpi = TcpDiscoverySpi().setIpFinder(ipFinder)


        val listener = CacheListener.Builder<String, String>()
        listener.onCreated {}


        return igniteConfiguration
    }

    override fun start(): Boolean = try {
        ignite = Ignition.start(configuration)
        true
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        false
    }

    override fun ignite(): Ignite = ignite

    override fun configuration(): IgniteConfiguration = configuration
}