package com.dp.commands;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.List;
import java.util.function.Consumer;

public interface PdtCommand {
    String getName();

    String getDescription();

    List<OptionType> getOptions();

    Consumer<CommandArguments> getCallback();

    CommandData toCommandData();
}
