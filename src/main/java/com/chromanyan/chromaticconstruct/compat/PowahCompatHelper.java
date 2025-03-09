package com.chromanyan.chromaticconstruct.compat;

import owmii.powah.api.PowahAPI;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.fluids.TinkerFluids;

public class PowahCompatHelper {

    private PowahCompatHelper() {}

    public static void initialize() {
        PowahAPI.registerMagmaticFluid(TinkerFluids.blazingBlood.getId(), 15000);

        // This is hacky and janky and I hate it.
        // Basically, the first one gets it to show up in JEI, the second one makes it actually function.
        PowahAPI.registerHeatSource(TinkerFluids.blazingBlood.getId(), 1500);
        PowahAPI.registerHeatSource(TConstruct.getResource("blazing_blood_fluid"), 1500);
    }
}
