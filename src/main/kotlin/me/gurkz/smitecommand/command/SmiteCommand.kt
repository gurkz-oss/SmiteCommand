package me.gurkz.smitecommand.command

import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.entity.EntityType
import net.minecraft.entity.LightningEntity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text

@Throws(CommandSyntaxException::class)
fun smite(ctx: CommandContext<ServerCommandSource>, message: String): Int {
    val target = EntityArgumentType.getPlayer(ctx, "target")

    val world = target.serverWorld
    val lightningEntity = LightningEntity(EntityType.LIGHTNING_BOLT, world)

    lightningEntity.setPosition(target.pos)

    world.spawnEntity(lightningEntity)

    ctx.source.sendFeedback({ Text.literal(message.replace("<target.name>", target.name.string)) }, false)
    return 1
}