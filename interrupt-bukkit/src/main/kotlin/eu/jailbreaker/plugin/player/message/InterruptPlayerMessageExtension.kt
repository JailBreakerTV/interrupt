package eu.jailbreaker.plugin.player.message

import eu.jailbreaker.plugin.adapter.IgniteConnectAdapter
import eu.jailbreaker.plugin.adapter.UniqueIdAdapter
import eu.jailbreaker.plugin.player.message.TitleModifyMessage.TitleModifyAction
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.title.Title

interface InterruptPlayerMessageExtension : IgniteConnectAdapter, UniqueIdAdapter {
    fun sendActionBar(component: Component) = ActionBarMessage(
        uniqueId(),
        GsonComponentSerializer.gson().serialize(component)
    ).send()

    fun sendMessage(component: Component) = ChatMessage(
        uniqueId(),
        GsonComponentSerializer.gson().serialize(component)
    ).send()

    fun clearTitle() = TitleModifyMessage(uniqueId(), TitleModifyAction.CLEAR).send()
    fun resetTitle() = TitleModifyMessage(uniqueId(), TitleModifyAction.CLEAR).send()

    fun sendTitle(title: Title) {
        val gson = GsonComponentSerializer.gson()
        val times = title.times() ?: Title.DEFAULT_TIMES
        TitleMessage(
            uniqueId(),
            gson.serialize(title.title()),
            gson.serialize(title.subtitle()),
            times.fadeIn().toMillis(),
            times.stay().toMillis(),
            times.fadeOut().toMillis()
        ).send()
    }
}