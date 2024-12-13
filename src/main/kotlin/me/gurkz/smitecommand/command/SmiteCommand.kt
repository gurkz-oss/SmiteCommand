package me.gurkz.smitecommand.command

import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import me.gurkz.smitecommand.MessageConfigKeys
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.entity.EntityType
import net.minecraft.entity.LightningEntity
import net.minecraft.server.command.ServerCommandSource

@Throws(CommandSyntaxException::class)
fun smite(ctx: CommandContext<ServerCommandSource>, messageConfig: Map<MessageConfigKeys, String?>): Int {
    val target = EntityArgumentType.getPlayer(ctx, "target")

    val world = target.serverWorld
    val lightningEntity = LightningEntity(EntityType.LIGHTNING_BOLT, world)

    lightningEntity.setPosition(target.pos)

    world.spawnEntity(lightningEntity)

    val miniMessage = MiniMessage.miniMessage()

    val senderMessage = messageConfig[MessageConfigKeys.SenderMessage]
    val targetMessage = messageConfig[MessageConfigKeys.TargetMessage]

    if (senderMessage != null) {
        ctx.source.sendMessage(miniMessage.deserialize(senderMessage, Placeholder.unparsed("target-name",
            target.name.string
        )))
    }

    if (targetMessage !== null) {
        target.sendMessage(miniMessage.deserialize(targetMessage))
    }


    return 1
}