package eu.jailbreaker.plugin.player.message

import eu.jailbreaker.connect.message.UniqueIdMessage
import eu.jailbreaker.plugin.IgniteNames
import java.util.*

class TitleModifyMessage(
    uuid: UUID,
    @JvmField
    val action: Int,
) : UniqueIdMessage(uuid, IgniteNames.TOPIC_USER_TITLE_MODIFY) {
    constructor(uuid: UUID, action: TitleModifyAction) : this(uuid, action.id)

    enum class TitleModifyAction(val id: Int) {
        CLEAR(0), RESET(1);

        companion object {
            fun findById(id: Int): TitleModifyAction? = values().firstOrNull { it.id == id }
        }
    }
}