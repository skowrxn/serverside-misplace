package pl.alpaqdev.misplace.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.alpaqdev.misplace.util.CustomLocation;

import java.util.*;

public class MisplaceUser {

    @Getter private String name;
    @Getter private UUID uuid;
    @Setter @Getter private double misplace;
    @Getter @Setter private CustomLocation lastMovePacket;
    private Map<UUID, List<CustomLocation>> recentPlayerPackets = new HashMap<>();

    public MisplaceUser(String name, UUID uuid, double misplace){
        this.name = name;
        this.uuid = uuid;
        this.misplace = misplace;
    }

    public void addPlayerPacket(final UUID playerUUID, final CustomLocation customLocation) {
        List<CustomLocation> customLocations = this.recentPlayerPackets.get(playerUUID);
        if (customLocations == null) {
            customLocations = new ArrayList<>();
        }
        if (customLocations.size() == 20) {
            customLocations.remove(0);
        }
        customLocations.add(customLocation);
        this.recentPlayerPackets.put(playerUUID, customLocations);
    }

    public CustomLocation getLastPlayerPacket(final UUID playerUUID, final int index) {
        final List<CustomLocation> customLocations = this.recentPlayerPackets.get(playerUUID);
        if (customLocations != null && customLocations.size() > index) {
            return customLocations.get(customLocations.size() - index);
        }
        return null;
    }

}
