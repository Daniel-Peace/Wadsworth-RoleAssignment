package com.dp.commands.utilities;

import com.dp.commands.CommandArguments;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class HelloWorldCommandArgs implements CommandArguments {
    private final SlashCommandInteractionEvent interactionEvent;

    public HelloWorldCommandArgs(SlashCommandInteractionEvent interactionEvent) {
        this.interactionEvent = interactionEvent;
    }

    @Override
    public SlashCommandInteractionEvent getInteractionEvent() {
        return interactionEvent;
    }
}
