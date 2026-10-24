package com.dp.bot.commands;

import com.dp.pdt.PdtCommand;
import com.dp.pdt.PdtCommandArguments;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.List;
import java.util.function.Consumer;

public class HelloWorldCommand implements PdtCommand
{
    private final String name = "hello-world";

    private final String description = "Says \"Hello world!\"";

    private final List<OptionType> optionTypes = List.of();

    private final Consumer<PdtCommandArguments> callback = (event) -> event.getEvent()
            .reply("Hello world!")
            .queue();

    boolean isProductionEnabled = false;

    public HelloWorldCommand()
    {

    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public List<OptionType> getOptions()
    {
        return optionTypes;
    }

    @Override
    public Consumer<PdtCommandArguments> getCallback()
    {
        return callback;
    }

    @Override
    public CommandData toCommandData()
    {
        return Commands.slash(name, description);
    }

    @Override
    public boolean isProductionEnabled()
    {
        return isProductionEnabled;
    }
}
