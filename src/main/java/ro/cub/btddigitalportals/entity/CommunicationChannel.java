package ro.cub.btddigitalportals.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum CommunicationChannel implements EnumClass<String> {

    EMAIL("EMAIL"),
    SMS("SMS");

    private final String id;

    CommunicationChannel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static CommunicationChannel fromId(String id) {
        for (CommunicationChannel at : CommunicationChannel.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}