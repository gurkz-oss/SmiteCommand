package me.gurkz.smitecommand

enum class MessageConfigKeys {
    SenderMessage,
    TargetMessage
}

class SmiteCommandConfig {
    val messageConfig = mapOf<MessageConfigKeys, String?>(
        MessageConfigKeys.SenderMessage to "<bold>struck <target-name> with lightning!</bold>",
        MessageConfigKeys.TargetMessage to "<bold>you got struck by lightning!</bold>"
    )
}