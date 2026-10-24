package com.dp.models.commands;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.List;
import java.util.function.Consumer;

/**
 * A simple class representing a Discord command. This includes all the
 * information required to register a command with the bot and upsert it to make
 * it available to guilds.
 */
public interface PdtCommand
{
    /**
     * Retrieves the name of this command.
     *
     * @return The name of this command.
     */
    String getName();

    /**
     * Retrieves the description of this command.
     *
     * @return The description of this command
     */
    String getDescription();

    /**
     * Retrieves the {@link OptionType OptionTypes} of this command.
     *
     * @return A {@link List} of the {@link OptionType OptionTypes} for this
     * command.
     */
    List<OptionType> getOptions();

    /**
     * Retrieves the {@link Consumer} of this command. This is the callback that
     * is run when the command is executed.
     *
     * @return The callback for this command.
     */
    Consumer<PdtCommandArguments> getCallback();

    /**
     * Converts this {@link PdtCommand} to a {@link CommandData}.
     *
     * @return The new {@link CommandData} representing this {@link PdtCommand}.
     */
    CommandData toCommandData();

    /**
     * Checks if this command is production ready or not.
     *
     * @return {@code true} is returned if the command is ready for production,
     * otherwise false is returned.
     */
    boolean isProductionEnabled();
}
