package ro.cub.btddigitalportals.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum QuestionOptions implements EnumClass<String> {

    YES("YES"),
    NO("NO");

    private final String id;

    QuestionOptions(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static QuestionOptions fromId(String id) {
        for (QuestionOptions at : QuestionOptions.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}