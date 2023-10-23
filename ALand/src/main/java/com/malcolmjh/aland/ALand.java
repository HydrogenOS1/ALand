//Class and Package imports
package com.malcolmjh.aland;
import com.malcolmjh.aland.commands.chatcommand;
import org.bukkit.plugin.java.JavaPlugin;
public final class ALand extends JavaPlugin {
    @Override
    public void onEnable() {
        //Start Logic
        getCommand("chat").setExecutor(new chatcommand());
        System.out.println("ALand Started!");
    }

    @Override
    public void onDisable() {
        //Stop Logic
        System.out.println("ALand has stoped");
    }
}


