package com.dp.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface CommandArguments {
    SlashCommandInteractionEvent getInteractionEvent();
}
