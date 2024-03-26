package ro.cub.btddigitalportals.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum QuestionOptionsExtended implements EnumClass<String> {

    YES("YES"),
    NO("NO"),
    DONT_KNOW("DONT_KNOW");

    private final String id;

    QuestionOptionsExtended(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static QuestionOptionsExtended fromId(String id) {
        for (QuestionOptionsExtended at : QuestionOptionsExtended.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}