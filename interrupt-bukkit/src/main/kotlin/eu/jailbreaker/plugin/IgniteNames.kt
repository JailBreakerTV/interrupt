package eu.jailbreaker.plugin

import eu.jailbreaker.connect.IgniteConnect
import java.util.*

object IgniteNames {
    const val TOPIC_USER_KICK = "user_kick"
    const val TOPIC_USER_CONNECT = "user_connect"
    const val TOPIC_USER_CHAT = "user_chat"
    const val TOPIC_USER_TITLE = "user_title"
    const val TOPIC_USER_TITLE_MODIFY = "user_title_modify"
    const val TOPIC_USER_ACTIONBAR = "user_actionbar"
    const val TOPIC_USER_SOUND = "user_sound"
    const val TOPIC_USER_STOP_SOUND = "user_stop_sound"
    const val TOPIC_USER_NOTE_SOUND = "user_note_sound"
    const val TOPIC_PLAYER_LIST_HEADER_FOOTER = "user_player_list_header_footer"

    val CACHE_ID_NAME = IgniteConnect.getInstance().cache<UUID, String>("id_name")
    val CACHE_NAME_ID = IgniteConnect.getInstance().cache<String, UUID>("name_id")
    val CACHE_ID_DISPLAY_NAME = IgniteConnect.getInstance().cache<UUID, String>("id_display_name")
    val CACHE_DISPLAY_NAME_ID = IgniteConnect.getInstance().cache<String, UUID>("display_name_id")
}