package com.chromanyan.chromaticconstruct.datagen.tconstruct;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class CCModifierIds {
    public static final ModifierId encumberment = id("encumberment");

    // compat: meaningful materials
    public static final ModifierId antiair = id("anti_air");
    public static final ModifierId antigravity = id("antigravity");
    public static final ModifierId moonbound = id("moonbound");

    // compat: enigmatic legacy
    public static final ModifierId slayer = id("slayer");
    public static final ModifierId emergencyProtection = id("emergency_protection");
    public static final ModifierId nemesis = id("nemesis");

    private static ModifierId id(String name) {
        return new ModifierId(ChromaticConstruct.MODID, name);
    }
}
