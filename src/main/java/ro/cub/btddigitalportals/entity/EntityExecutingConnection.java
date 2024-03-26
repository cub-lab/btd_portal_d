package ro.cub.btddigitalportals.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum EntityExecutingConnection implements EnumClass<String> {

    BTD("BTD"),
    OP_ECONOMIC("OP_ECONOMIC");

    private final String id;

    EntityExecutingConnection(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static EntityExecutingConnection fromId(String id) {
        for (EntityExecutingConnection at : EntityExecutingConnection.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}