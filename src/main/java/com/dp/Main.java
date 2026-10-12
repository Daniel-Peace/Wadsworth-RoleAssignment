package com.dp;

import com.dp.commands.HelloWorldCommand;
import com.dp.commands.utilities.CommandRegistrar;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.Optional;

public class Main {
    static void main(String[] args) {
        JDA api = startBot().orElse(null);

        if (api == null) {
            System.out.println("[-] Failed to start bot.\n Exiting...");
            System.out.println("[-] Exiting...");

            System.exit(1);
        }

        CommandRegistrar.registerCommand(api, new HelloWorldCommand());
    }

    /**
     * Retrieves the token for the bot from the .env file
     * and starts the bot.
     *
     * @return If successful, the JDA instance is returned
     * wrapped in an optional, otherwise the
     * {@link Optional} is returned empty.
     */
    private static Optional<JDA> startBot() {
        String botToken = getBotToken().orElse(null);

        if (botToken == null) {
            return Optional.empty();
        }

        return Optional.of(JDABuilder.createDefault(botToken).build());
    }

    private static Optional<String> getBotToken() {
        Dotenv dotenv = Dotenv.load();

        String token = dotenv.get("BOT_TOKEN");

        if (token == null || token.isBlank()) {
            return Optional.empty();
        }

        return Optional.of(token);
    }

    private static void registerCommands(JDA api) {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand();

        Guild guild = api.getGuildById("1373317835178381332");
        if (guild != null) {
            guild.upsertCommand(
                            helloWorldCommand.getName(),
                            helloWorldCommand.getDescription())
                    .queue();
        }

        api.updateCommands().addCommands(
                        Commands.slash(helloWorldCommand.getName(), helloWorldCommand.getDescription()))
                .queue();
    }
}
