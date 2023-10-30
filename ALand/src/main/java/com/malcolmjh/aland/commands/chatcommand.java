//Classes and Packages Import
package com.malcolmjh.aland.commands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//Chat Command Code
public class chatcommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        //Check if the command sender is a player.
        if (sender instanceof Player) {
            Player p = (Player) sender;
            //Check if the command has arguments.
            if (args.length == 0) {
                //If no args return the error
                p.sendMessage(ChatColor.RED + "Unknown or incomplete command, see below for error");
                p.sendMessage(ChatColor.GRAY + "chat" + ChatColor.RED + "<--[HERE]");
                p.sendMessage(ChatColor.DARK_GREEN + "Usage: /chat <message>");
            }else {
                //Make the "name" var Pt1
                StringBuilder builder = new StringBuilder();
                for(int i = 0; i < args.length; i++) {
                    builder.append(args[i]);
                    builder.append(" ");
            }
            //Make the "name" var Pt2
            String name = builder.toString();
            name = name.stripTrailing();
            //Chat API call
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://api.malcolmjh.com:8080/check_name?name=" + name))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = null;
            try {
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                    throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // Print the response
            p.sendMessage(ChatColor.DARK_BLUE + response.body());
            }
        }
        return true;
    }
}