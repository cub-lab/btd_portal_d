package ro.cub.btddigitalportals.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum ConsumptionDeviceType implements EnumClass<String> {

    PREDEFINED("PREDEFINED"),
    USER_DEFINED("USER_DEFINED");

    private final String id;

    ConsumptionDeviceType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ConsumptionDeviceType fromId(String id) {
        for (ConsumptionDeviceType at : ConsumptionDeviceType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}