package eu.jailbreaker.connect

import org.apache.ignite.configuration.IgniteConfiguration

interface IgniteConfigurationAware {
    fun configuration(): IgniteConfiguration
}