package com.connorlinfoot.regionvanish;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Main extends JavaPlugin implements Listener {
    private static Plugin instance;
    public static WorldGuardPlugin worldGuardPlugin;
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        Server server = getServer();
        ConsoleCommandSender console = server.getConsoleSender();
        worldGuardPlugin = getWorldGuard();

        console.sendMessage(ChatColor.GREEN + "=========== RegionVanish ===========");
        console.sendMessage(ChatColor.GREEN + "=========== VERSION: 1.0 ===========");
        console.sendMessage(ChatColor.GREEN + "======== BY CONNOR LINFOOT! ========");
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    public WorldGuardPlugin getWorldGuard(){
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if ((plugin == null) || (!(plugin instanceof WorldGuardPlugin))){
            return null;
        }
        return (WorldGuardPlugin)plugin;
    }

    public void onDisable() {
        getLogger().info(getDescription().getName() + " has been disabled!");
    }

    @EventHandler
    public void onPlayerMove( PlayerMoveEvent e ){
        Block block = e.getPlayer().getLocation().getWorld().getHighestBlockAt(e.getPlayer().getLocation());
        if( block == null ) return;

        if( block.getType() == Material.SPONGE ){
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,9999,9999));
        } else {
            e.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
        }
    }
}
