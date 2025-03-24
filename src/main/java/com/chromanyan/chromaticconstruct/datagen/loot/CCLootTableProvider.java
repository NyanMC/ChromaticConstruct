package com.chromanyan.chromaticconstruct.datagen.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

// thanks tinkers construct
public class CCLootTableProvider extends LootTableProvider {

    public CCLootTableProvider(PackOutput pOutput) {
        super(pOutput, Set.of(), List.of(new SubProviderEntry(CCBlockLootTableProvider::new, LootContextParamSets.BLOCK)));
    }

    /*
    @Override
    public @NotNull List<SubProviderEntry> getTables() {
        return lootTables;
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @NotNull ValidationContext validationtracker) {
        map.forEach((name, loc) -> loc.validate(validationtracker.setParams(loc.getParamSet()).enterElement("{" + name + "}", new LootDataId<>(LootDataType.TABLE, name))));
        // Remove vanilla's tables, which we also loaded so we can redirect stuff to them.
        // This ensures the remaining generator logic doesn't write those to files.
        map.keySet().removeIf((loc) -> !loc.getNamespace().equals(ChromaticConstruct.MODID));
    }
    */
}
