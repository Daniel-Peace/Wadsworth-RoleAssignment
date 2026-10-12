package com.dp.commands.utilities;

import com.dp.commands.PdtCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Set;
import java.util.stream.Collectors;

public class CommandRegistrar {
    private CommandRegistrar() {

    }

    public static void registerCommand(JDA jda, PdtCommand command) {
        if (jda == null || command == null) {
            return;
        }

        jda.updateCommands().addCommands(command.toCommandData()).queue();
        jda.addEventListener(new ListenerAdapter() {
            @Override
            public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
                command.getCallback().accept(new HelloWorldCommandArgs(event));
            }
        });
    }

    public static void registerCommands(JDA jda, Set<PdtCommand> commands) {
        if (jda == null || commands.isEmpty()) {
            return;
        }

        jda.updateCommands()
                .addCommands(commands.stream().map(PdtCommand::toCommandData)
                        .collect(Collectors.toSet()))
                .queue();
    }
}
