package eu.jailbreaker.plugin.bukkit

import eu.jailbreaker.connect.IgniteConnect
import eu.jailbreaker.connect.message.MessageListener
import eu.jailbreaker.plugin.InterruptManager
import eu.jailbreaker.plugin.bukkit.event.BukkitNodePreStartEvent
import eu.jailbreaker.plugin.bukkit.event.BukkitNodeStartedEvent
import net.kyori.adventure.key.Key
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.PluginLoadOrder
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.annotation.plugin.ApiVersion
import org.bukkit.plugin.java.annotation.plugin.LoadOrder
import org.bukkit.plugin.java.annotation.plugin.Plugin
import org.bukkit.plugin.java.annotation.plugin.Website
import org.bukkit.plugin.java.annotation.plugin.author.Author
import org.reflections.Reflections
import java.time.Duration
import java.util.concurrent.CompletableFuture

lateinit var interruptBukkitPlugin: InterruptBukkit
lateinit var audience: BukkitAudiences

@ApiVersion(ApiVersion.Target.v1_15)
@Author("JailBreaker")
@LoadOrder(PluginLoadOrder.POSTWORLD)
@Website("https://jailbreaker.eu")
@Plugin(name = "InterruptBukkit", version = "0.0.1")
class InterruptBukkit : JavaPlugin(), Listener {
    private lateinit var connect: IgniteConnect
    private val messageListener: MutableList<MessageListener<*>> = mutableListOf()
    private val reflections = Reflections("eu.jailbreaker.plugin")

    override fun onEnable() {
        interruptBukkitPlugin = this
        audience = BukkitAudiences.create(this)
        server.pluginManager.registerEvents(this, this)
        connect = IgniteConnect.getInstance()
        BukkitNodePreStartEvent(connect.configuration()).callEvent()
        CompletableFuture.supplyAsync { connect.start() }.whenComplete { status, _ ->
            if (status) {
                reflections.getSubTypesOf(MessageListener::class.java)
                    .mapNotNull { it.constructors.firstOrNull() }
                    .mapNotNull { it.newInstance() }
                    .mapNotNull { it as? MessageListener<*> }
                    .forEach {
                        messageListener.add(it)
                        it.register()
                    }
                Bukkit.getScheduler().runTask(this, Runnable { BukkitNodeStartedEvent(connect).callEvent() })
            }
        }
    }

    override fun onDisable() {
        messageListener.forEach { it.register() }
        connect.close()
    }

    @EventHandler
    fun onBukkitNodePreStart(event: BukkitNodePreStartEvent) {
        val config = event.igniteConfiguration
        Bukkit.getLogger().severe("VERSION: $config")
    }

    @EventHandler
    fun onBukkitNodeStarted(event: BukkitNodeStartedEvent) {
        Bukkit.getLogger().info("NODE STARTED SUCCESSFULLY")
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK) {
            event.item?.let { item ->
                when (item.type) {
                    Material.STICK -> InterruptManager.findPlayer(event.player.uniqueId)
                        .sendActionBar(Component.text("§eHALLO BRUDER"))
                    Material.DIAMOND_SWORD -> InterruptManager.findPlayer(event.player.uniqueId)
                        .sendMessage(Component.text("§eTEST MESSAGE"))
                    Material.IRON_SWORD -> InterruptManager.findPlayer(event.player.uniqueId)
                        .sendTitle(
                            Title.title(
                                Component.text("§bTITLE"),
                                Component.text("§cSUBTITLE"),
                                Title.Times.of(Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofSeconds(2))
                            )
                        )
                    Material.STONE_SWORD -> InterruptManager.findPlayer(event.player.uniqueId)
                        .playSound(
                            org.bukkit.Sound.ENTITY_PLAYER_LEVELUP.toKyori(
                                volume = 10F,
                                pitch = 10F
                            )
                        )
                    Material.WOODEN_SWORD -> InterruptManager.findPlayer(event.player.uniqueId)
                        .playerListHeaderFooter(
                            Component.text("§eJailBreaker.eu"),
                            Component.text("§7Minecraft Server")
                        )
                    else -> {
                    }
                }
            }
        }
    }

    fun org.bukkit.Sound.toKyori(source: Sound.Source = Sound.Source.NEUTRAL, volume: Float, pitch: Float): Sound =
        Sound.sound(Key.key(key.namespace, key.key), source, volume, pitch)
}