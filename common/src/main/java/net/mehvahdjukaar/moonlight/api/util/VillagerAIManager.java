package net.mehvahdjukaar.moonlight.api.util;

import com.google.common.collect.ImmutableList;
import net.mehvahdjukaar.moonlight.core.Moonlight;
import net.mehvahdjukaar.moonlight.core.misc.IVillagerBrainEvent;
import net.mehvahdjukaar.moonlight.core.mixins.accessor.VillagerAccessor;
import net.mehvahdjukaar.moonlight.api.platform.event.EventHelper;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import java.util.function.Consumer;

public class VillagerAIManager {

    /**
     * Register an event listener for the villager brain event.
     * On forge Use the subscribe event annotation instead
     */
    public static void addListener(Consumer<IVillagerBrainEvent> eventConsumer){
        EventHelper.addListener(eventConsumer, IVillagerBrainEvent.class);
    }

    /**
     * adds a memory module to the villager brain when it's created. Add here and not in the event if that memory needs to be saved, otherwise it will not be loaded since the event is called after the brain is deserialized from tag
     */
    public static void registerMemory(MemoryModuleType<?> memoryModuleType) {

        try {

            var oldValue = VillagerAccessor.getMemoryTypes();

            ImmutableList.Builder<MemoryModuleType<?>> builder = ImmutableList.builder();
            builder.addAll(oldValue);
            builder.add(memoryModuleType);
            VillagerAccessor.setMemoryTypes(builder.build());

        } catch (Exception e) {
            Moonlight.LOGGER.warn("failed to register pumpkin sensor type for villagers: " + e);
        }
    }

}