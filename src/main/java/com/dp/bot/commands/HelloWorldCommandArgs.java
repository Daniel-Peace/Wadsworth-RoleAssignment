package com.dp.models.commands.implementations;

import com.dp.pdt.PdtCommandArguments;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class HelloWorldCommandArgs implements PdtCommandArguments
{
    private final SlashCommandInteractionEvent interactionEvent;

    public HelloWorldCommandArgs(SlashCommandInteractionEvent interactionEvent)
    {
        this.interactionEvent = interactionEvent;
    }

    @Override
    public SlashCommandInteractionEvent getEvent()
    {
        return interactionEvent;
    }
}
