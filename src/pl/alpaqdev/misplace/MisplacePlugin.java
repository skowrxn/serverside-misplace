package pl.alpaqdev.misplace;

import gg.ragemc.spigot.RageSpigot;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;
import pl.alpaqdev.misplace.command.XpCommand;
import pl.alpaqdev.misplace.listener.PacketListener;
import pl.alpaqdev.misplace.manager.ProfileManager;

public class MisplacePlugin extends JavaPlugin {

    @Getter private static ProfileManager profileManager;

    @Override
    public void onEnable(){
        if(validSpigot()) {
            profileManager = new ProfileManager();
            ((CraftServer) getServer()).getCommandMap().register("xp", new XpCommand());
            RageSpigot.INSTANCE.addPacketHandler(new PacketListener());
        }

    }

    private boolean validSpigot(){
        try{
            Class.forName("gg.ragemc.spigot.RageSpigot");
        }catch (ClassNotFoundException ex){
            getLogger().severe("Plugin requires RageSpigot to run! Its free so ye download it or idk use a spigot with PacketHandlers");
            Bukkit.getPluginManager().disablePlugin(this);
            return false;
        }

        return true;
    }


}
