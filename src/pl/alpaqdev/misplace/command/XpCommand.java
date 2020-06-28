package pl.alpaqdev.misplace.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import pl.alpaqdev.misplace.MisplacePlugin;
import pl.alpaqdev.misplace.base.MisplaceUser;

public class XpCommand extends BukkitCommand {

    public XpCommand(){
        super("xp");
    }


    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args) {

        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("Only players can execute this command");
            return false;
        }

        Player p = (Player)commandSender;

        if(!p.hasPermission("cmd.xp")){
            p.sendMessage(ChatColor.RED + "You don't have permission to use this command");
            return false;
        }

        if(args.length != 1){
            p.sendMessage(ChatColor.RED + "Usage: /xp <amount>");
            return false;
        }

        try {

            double misplace = Double.parseDouble(args[0]);
            MisplaceUser misplaceUser = MisplacePlugin.getProfileManager().getUser(p);

            misplaceUser.setMisplace(misplace);
            commandSender.sendMessage(ChatColor.GREEN + "Set player's xp");

        } catch (Exception ex){
            p.sendMessage(ChatColor.RED + "Argument must be a double (ex: 0.25)");
            return false;
        }





        return false;
    }
}
