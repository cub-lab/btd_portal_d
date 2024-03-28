package ro.cub.btddigitalportals.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum TerrainType implements EnumClass<String> {

    CONCRIT_ASPHALT("CONCRIT_ASPHALT"),
    PAVEMENT("PAVEMENT"),
    SOIL_GREEN("SOIL_GREEN");

    private final String id;

    TerrainType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TerrainType fromId(String id) {
        for (TerrainType at : TerrainType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}