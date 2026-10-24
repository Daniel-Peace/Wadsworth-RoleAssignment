package com.dp;

import com.dp.bot.utilties.WadsworthUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.internal.interactions.CommandDataImpl;

import java.util.Optional;
import java.util.Set;

/**
 * This is the main class
 */
public class Main
{
    /**
     * The guild IDs of the test guilds.
     */
    private static final Set<String> TEST_GUILD_IDS = Set.of(
            "1373317835178381332");

    /**
     * The main entry point into this bot.
     *
     * @param args Arguments that were passed in when running this program.
     */
    static void main(String[] args)
    {
        // Retrieve the bot token from the .env file
        String token = WadsworthUtils.getBotToken().orElse(null);

        if (token == null)
        {
            System.out.println("Failed to fetch token from .env. Exiting...");

            System.exit(1);
        }

        System.out.println("[-] Successfully retrieved token...");

        // Initialize the JDA instance. This calls awaitReady(), so there is no
        // need to use an onReady listener and no need to worry about
        // asynchronous behavior here
        JDA jda = initializeJda(token).orElse(null);

        if (jda == null)
        {
            System.out.println("Failed to initialize JDA instance. Exiting...");

            System.exit(1);
        }

        System.out.println("[-] Successfully initialized JDA instance...");

        CommandData pingCommandData = new CommandDataImpl("ping", "Says pong in response");

        CommandData helloWorldCommandData = new CommandDataImpl("hello-world", "Hello world!");

        Set<CommandData> commands = Set.of(pingCommandData, helloWorldCommandData);

        upsertToTestGuilds(jda, commands);

        jda.addEventListener(new ListenerAdapter()
        {
            @Override
            public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
            {
                String fullCommandName = event.getFullCommandName();

                if (fullCommandName.isBlank())
                {
                    return;
                }

                if (fullCommandName.equals(pingCommandData.getName()))
                {
                    event.reply("Pong").queue();
                } else if (fullCommandName.equals(helloWorldCommandData.getName()))
                {
                    event.reply("Hello world!").queue();
                }
            }
        });
    }

    /**
     * Initializes a new instance of {@link JDA}.
     *
     * @param token The token to use for the new instance of {@link JDA}.
     * @return The newly created instance of {@link JDA} is returned
     * wrapped in an {@link Optional} upon,otherwise the {@link Optional} is
     * returned empty.
     */
    private static Optional<JDA> initializeJda(String token)
    {
        if (token == null || token.isBlank())
        {
            return Optional.empty();
        }

        try
        {
            return Optional.of(JDABuilder.createDefault(token).build().awaitReady());

        } catch (InterruptedException interruptedException)
        {
            return Optional.empty();
        }
    }

    private static void upsertToTestGuilds(JDA jda, Set<CommandData> commandDataSet)
    {
        if (TEST_GUILD_IDS.isEmpty())
        {
            return;
        }

        if (jda == null)
        {
            return;
        }

        if (commandDataSet.isEmpty())
        {
            return;
        }

        for (String guildId : TEST_GUILD_IDS)
        {
            Guild guild = jda.getGuildById(guildId);

            if (guild == null)
            {
                continue;
            }

            for (CommandData commandData : commandDataSet)
            {
                guild.upsertCommand(commandData).queue();
            }
        }
    }
}
