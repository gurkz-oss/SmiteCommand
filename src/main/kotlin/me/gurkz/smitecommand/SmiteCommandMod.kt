package me.gurkz.smitecommand

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.context.CommandContext
import me.gurkz.smitecommand.command.smite
import me.lucko.fabric.api.permissions.v0.Permissions
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.command.argument.EntityArgumentType
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.CommandManager.RegistrationEnvironment
import net.minecraft.server.command.ServerCommandSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.function.Consumer

object SmiteCommandMod : ModInitializer {
    private val COMMANDS = ArrayList<LiteralArgumentBuilder<ServerCommandSource?>>()
    private const val MOD_ID: String = "smitecommand"
    private val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    private fun addCommand(command: LiteralArgumentBuilder<ServerCommandSource?>) {
        COMMANDS.add(command)
    }

    override fun onInitialize() {
        LOGGER.info("hello from smite command 1.0.0")

        addCommand(
            CommandManager.literal("smite").requires(Permissions.require("smitecommand.smite", 4)).then(
                CommandManager.argument("target", EntityArgumentType.player())
                    .executes { ctx: CommandContext<ServerCommandSource> ->
                        smite(ctx)
                    })
        )

        CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback { dispatcher: CommandDispatcher<ServerCommandSource?>, registry: CommandRegistryAccess?, environment: RegistrationEnvironment? ->
            COMMANDS.forEach(
                Consumer { command: LiteralArgumentBuilder<ServerCommandSource?>? -> dispatcher.register(command) })
        })
    }
}
