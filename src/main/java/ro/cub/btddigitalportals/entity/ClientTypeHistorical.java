package ro.cub.btddigitalportals.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum ClientTypeHistorical implements EnumClass<String> {

    NEW("NEW"),
    EXISTING("EXISTING");

    private final String id;

    ClientTypeHistorical(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ClientTypeHistorical fromId(String id) {
        for (ClientTypeHistorical at : ClientTypeHistorical.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}