package com.dp.models.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface PdtCommandArguments
{
    SlashCommandInteractionEvent getEvent();
}
