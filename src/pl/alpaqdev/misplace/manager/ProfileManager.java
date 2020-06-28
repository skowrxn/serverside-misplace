package pl.alpaqdev.misplace.manager;

import org.bukkit.entity.Player;
import pl.alpaqdev.misplace.base.MisplaceUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileManager {

    private List<MisplaceUser> users;

    public ProfileManager(){
        users = new ArrayList<>();
    }

    public MisplaceUser getUser(Player player){
        return this.users.stream().filter(user -> user.getUuid().equals(player.getUniqueId())).findFirst().orElse(new MisplaceUser(player.getName(), player.getUniqueId(), 0.0));
    }

}
