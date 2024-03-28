package ro.cub.btddigitalportals.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum DevicePositioning implements EnumClass<String> {

    LEFT("LEFT"),
    RIGHT("RIGHT"),
    OTHER_POS("OTHER_POS");

    private final String id;

    DevicePositioning(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static DevicePositioning fromId(String id) {
        for (DevicePositioning at : DevicePositioning.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}