package me.gurkz.smitecommand.command

import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import me.gurkz.smitecommand.MessageConfigKeys
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.entity.EntityType
import net.minecraft.entity.LightningEntity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

@Throws(CommandSyntaxException::class)
fun smite(ctx: CommandContext<ServerCommandSource>, messageConfig: Map<MessageConfigKeys, String?>): Int {
    val target = EntityArgumentType.getPlayer(ctx, "target")

    val world = target.serverWorld
    val lightningEntity = LightningEntity(EntityType.LIGHTNING_BOLT, world)

    lightningEntity.setPosition(target.pos)

    world.spawnEntity(lightningEntity)

    if (messageConfig[MessageConfigKeys.SenderMessage] != null) {
        ctx.source.sendFeedback({ Text.literal(messageConfig[MessageConfigKeys.SenderMessage]?.replace("<target.name>", target.name.string)) }, false)
    }

    if (messageConfig[MessageConfigKeys.TargetMessage] !== null) {
       target.sendMessage(Text.literal(messageConfig[MessageConfigKeys.TargetMessage]))
    }


    return 1
}