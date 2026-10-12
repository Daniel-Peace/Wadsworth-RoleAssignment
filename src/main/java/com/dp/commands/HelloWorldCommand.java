package com.dp.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.List;
import java.util.function.Consumer;

public class HelloWorldCommand implements PdtCommand {

    private final String name = "hello-world";

    private final String description = "Says \"Hello world!\"";

    private final List<OptionType> optionTypes = List.of();

    private final Consumer<CommandArguments> callback = (event) -> {
        event.getInteractionEvent().reply("Hello world!").queue();
    };

    public HelloWorldCommand() {

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public List<OptionType> getOptions() {
        return optionTypes;
    }

    @Override
    public Consumer<CommandArguments> getCallback() {
        return callback;
    }

    @Override
    public CommandData toCommandData() {
        return Commands.slash(name, description);
    }
}
