package ro.cub.btddigitalportals.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

@JmixEntity
@Entity(name = "cub_ClientExtUser")
public class ClientExtUser extends User {

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private OffsetDateTime createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private OffsetDateTime lastModifiedDate;

    @Column(name = "TYPE_")
    private String type;

    @Pattern(message = "{msg://ro.cub.btddigitalportals.entity/ClientExtUser.phone.validation.Pattern}", regexp = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$")
    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ACCEPTED_TERMS_AND_CONDITIONS")
    private Boolean acceptedTermsAndConditions;

    @Column(name = "ACTIVATION_CODE")
    private String activationCode;

    @Column(name = "PREFERRED_COMMUNICATION_CHANNEL", nullable = false)
    @NotNull
    private String preferredCommunicationChannel;

    public CommunicationChannel getPreferredCommunicationChannel() {
        return preferredCommunicationChannel == null ? null : CommunicationChannel.fromId(preferredCommunicationChannel);
    }

    public void setPreferredCommunicationChannel(CommunicationChannel preferredCommunicationChannel) {
        this.preferredCommunicationChannel = preferredCommunicationChannel == null ? null : preferredCommunicationChannel.getId();
    }

    public ClientType getType() {
        return type == null ? null : ClientType.fromId(type);
    }

    public void setType(ClientType type) {
        this.type = type == null ? null : type.getId();
    }

    public Boolean getAcceptedTermsAndConditions() {
        return acceptedTermsAndConditions;
    }

    public void setAcceptedTermsAndConditions(Boolean acceptedTermsAndConditions) {
        this.acceptedTermsAndConditions = acceptedTermsAndConditions;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OffsetDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(OffsetDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public void setPreferredCommunicationChannel(String preferredCommunicationChannel) {
        this.preferredCommunicationChannel = preferredCommunicationChannel;
    }
}