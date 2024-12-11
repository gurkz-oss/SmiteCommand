package me.gurkz.smitecommand

enum class MessageConfigKeys {
    SenderMessage,
    TargetMessage
}

class SmiteCommandConfig {
    val messageConfig = mapOf<MessageConfigKeys, String?>(
        MessageConfigKeys.SenderMessage to "struck <target.name> with lightning!",
        MessageConfigKeys.TargetMessage to "you got struck by lightning!"
    )
}