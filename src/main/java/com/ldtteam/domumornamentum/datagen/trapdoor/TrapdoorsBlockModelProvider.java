package com.ldtteam.domumornamentum.datagen.trapdoor;

import com.ldtteam.datagenerators.models.block.BlockModelJson;
import com.ldtteam.domumornamentum.block.types.TrapdoorType;
import com.ldtteam.domumornamentum.util.Constants;
import com.ldtteam.domumornamentum.util.DataGeneratorConstants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class TrapdoorsBlockModelProvider implements DataProvider
{
    private final DataGenerator generator;

    public TrapdoorsBlockModelProvider(final DataGenerator generator)
    {
        this.generator = generator;
    }

    @Override
    public void run(@NotNull HashCache cache) throws IOException
    {
        Set<String> uniqueValues = new HashSet<>();
        for (TrapdoorType trapdoorsShape : TrapdoorType.values())
        {
            String shapeName = trapdoorsShape.getSerializedName();
            if (uniqueValues.add(shapeName))
            {
                final BlockModelJson modelJson = new BlockModelJson();

                modelJson.setLoader(Constants.MATERIALLY_TEXTURED_MODEL_LOADER.toString());
                modelJson.setParent(new ResourceLocation(Constants.MOD_ID, "block/trapdoors/trapdoor_" + shapeName + "_spec").toString());

                final String name = "trapdoor_" + shapeName + ".json";
                final Path saveFile = this.generator.getOutputFolder().resolve(DataGeneratorConstants.TRAPDOORS_BLOCK_MODELS_DIR).resolve(name);

                DataProvider.save(DataGeneratorConstants.GSON, cache, DataGeneratorConstants.serialize(modelJson), saveFile);
            }
        }
    }

    @Override
    @NotNull
    public String getName()
    {
        return "Trapdoors Block Model Provider";
    }
}
