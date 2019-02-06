package poke_models.common;

import com.google.gson.Gson;
import poke_models.games.VersionGroup;
import poke_models.machines.Machine;

public class MachineVersionDetail {
    // The machine that teaches a move from an item
    private Machine machine;

    // The version group of this specific machine
    private VersionGroup version_group;

    public Machine getMachine() {
        return machine;
    }

    public MachineVersionDetail setMachine(Machine machine) {
        this.machine = machine;
        return this;
    }

    public VersionGroup getVersionGroup() {
        return version_group;
    }

    public MachineVersionDetail setVersionGroup(VersionGroup version_group) {
        this.version_group = version_group;
        return this;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}