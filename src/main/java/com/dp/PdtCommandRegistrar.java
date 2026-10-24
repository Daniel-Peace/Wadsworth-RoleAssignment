package com.dp.utilities;

import com.dp.models.commands.PdtCommand;
import com.dp.models.commands.implementations.HelloWorldCommandArgs;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A registrar that handles registering
 * {@link PdtCommand PdtCommands} with the bot, test
 * servers, and "production" servers.
 */
public class PdtCommandRegistrar
{

    private static final PdtCommandRegistrar INSTANCE = new PdtCommandRegistrar();

    public PdtCommandRegistrar getSingleInstance()
    {
        return INSTANCE;
    }


    /**
     * A {@link Set} containing the IDs of test servers we
     * want to add the commands to.
     */
    private final static Set<String> GUILD_IDS = Set.of(
            "1373317835178381332",
            "1375944720257253467");

    /**
     * A private constructor to ensure this class stays
     * static.
     */
    private PdtCommandRegistrar()
    {

    }

    /**
     * Registers a {@link PdtCommand} with the test servers,
     * production servers, and the bot.
     *
     * @param jda     The {@link JDA} instance to register
     *                the commands with.
     * @param command The {@link PdtCommand} to register.
     */
    public static void registerCommand(JDA jda, PdtCommand command)
    {
        if (jda == null || command == null)
        {
            return;
        }

        registerCommandWithBot(jda, command);

        registerCommandWithTestServers(jda, command);

        registerCommandWithServers(jda, command);
    }

    /**
     * Registers the given {@link Set} of
     * {@link PdtCommand PdtCommands} with the test servers,
     * production servers, and the bot.
     *
     * @param jda      The {@link JDA} instance to register
     *                 the commands with.
     * @param commands The {@link PdtCommand PdtCommands} to
     *                 register.
     */
    public static void registerCommands(JDA jda, Set<PdtCommand> commands)
    {
        if (jda == null || commands.isEmpty())
        {
            return;
        }

        registerCommandsWithBot(jda, commands);

        registerCommandsWithTestServers(jda, commands);

        registerCommandsWithServers(jda, commands);
    }

    /**
     * Register the given {@link PdtCommand} with the bot
     * referenced by the given {@link JDA} instance.
     *
     * @param jda     The instance of {@link JDA} to
     *                register the command with.
     * @param command The {@link PdtCommand} to register.
     */
    private static void registerCommandWithBot(JDA jda, PdtCommand command)
    {
        if (jda == null || command == null)
        {
            return;
        }

        jda.updateCommands().addCommands(command.toCommandData()).queue();

        jda.addEventListener(new ListenerAdapter()
        {
            @Override
            public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event)
            {
                command.getCallback().accept(new HelloWorldCommandArgs(event));
            }
        });
    }

    /**
     * Registers the given {@link PdtCommand PdtCommands}
     * with the bot represented by the given {@link JDA}
     * instance.
     *
     * @param jda      The {@link JDA} instance to register
     *                 the {@link PdtCommand PdtCommands}
     *                 with.
     * @param commands The {@link PdtCommand PdtCommands}
     *                 to register.
     */
    private static void registerCommandsWithBot(JDA jda, Set<PdtCommand> commands)
    {
        if (jda == null || commands.isEmpty())
        {
            return;
        }

        jda.updateCommands()
                .addCommands(commands.stream().map(PdtCommand::toCommandData).collect(Collectors.toSet()))
                .queue();

        for (PdtCommand command : commands)
        {
            jda.addEventListener(new ListenerAdapter()
            {
                @Override
                public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event)
                {
                    command.getCallback().accept(new HelloWorldCommandArgs(event));
                }
            });
        }
    }

    private static void registerCommandWithTestServers(JDA jda, PdtCommand command)
    {
        if (GUILD_IDS.isEmpty() || jda == null || command == null)
        {
            return;
        }

        for (String guildId : GUILD_IDS)
        {
            Guild guild = jda.getGuildById(guildId);

            if (guild != null)
            {
                guild.upsertCommand(command.getName(), command.getDescription()).queue();
            }
        }
    }

    private static void registerCommandsWithTestServers(JDA jda, Set<PdtCommand> commands)
    {
        if (GUILD_IDS.isEmpty() || jda == null || commands == null || commands.isEmpty())
        {
            return;
        }

        for (String guildId : GUILD_IDS)
        {
            for (PdtCommand command : commands)
            {
                Guild guild = jda.getGuildById(guildId);

                if (guild != null)
                {
                    guild.upsertCommand(command.getName(), command.getDescription()).queue();
                }
            }
        }
    }

    private static void registerCommandWithServers(JDA jda, PdtCommand command)
    {
        if (jda == null || command == null)
        {
            return;
        }

        if (!command.isProductionEnabled())
        {
            return;
        }

        // TODO: Add command to all production servers
    }

    private static void registerCommandsWithServers(JDA jda, Set<PdtCommand> commands)
    {
        if (jda == null || commands == null || commands.isEmpty())
        {
            return;
        }

        for (PdtCommand command : commands)
        {
            if (!command.isProductionEnabled())
            {
                continue;
            }

            // TODO: Add command to all production servers
        }
    }
}
