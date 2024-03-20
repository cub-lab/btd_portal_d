package ro.cub.btddigitalportals.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "CUB_LEGAL_CLIENT")
@Entity(name = "cub_LegalClient")
public class LegalClient {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private OffsetDateTime createdDate;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private OffsetDateTime lastModifiedDate;

    @Column(name = "COMPANY", length = 500)
    private String company;

    @Column(name = "NO_CHAMBER_OF_COMMERCE")
    private String noChamberOfCommerce;

    @Column(name = "CIF_CUI")
    private String cifCUI;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "CONTACT_FIRST_NAME")
    private String contactFirstName;

    @Column(name = "CONTACT_LAST_NAME")
    private String contactLastName;

    @Pattern(message = "{msg://ro.cub.btddigitalportals.entity/LegalClient.phoneNumber.validation.Pattern}", regexp = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$")
    @Column(name = "CONTACT_PHONE_NUMBER")
    private String contactPhoneNumber;

    @Column(name = "CONTACT_FAX_NUMBER")
    private String contactFaxNumber;

    @Column(name = "COMMUNICATION_CHANNEL")
    private String communicationChannel;

    @Column(name = "ACCEPTED_TERMS_AND_CONDITIONS")
    private Boolean acceptedTermsAndConditions;

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactFaxNumber() {
        return contactFaxNumber;
    }

    public void setContactFaxNumber(String contactFaxNumber) {
        this.contactFaxNumber = contactFaxNumber;
    }

    public Boolean getAcceptedTermsAndConditions() {
        return acceptedTermsAndConditions;
    }

    public void setAcceptedTermsAndConditions(Boolean acceptedTermsAndConditions) {
        this.acceptedTermsAndConditions = acceptedTermsAndConditions;
    }

    public CommunicationChannel getCommunicationChannel() {
        return communicationChannel == null ? null : CommunicationChannel.fromId(communicationChannel);
    }

    public void setCommunicationChannel(CommunicationChannel communicationChannel) {
        this.communicationChannel = communicationChannel == null ? null : communicationChannel.getId();
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String phoneNumber) {
        this.contactPhoneNumber = phoneNumber;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCifCUI() {
        return cifCUI;
    }

    public void setCifCUI(String cifCUI) {
        this.cifCUI = cifCUI;
    }

    public String getNoChamberOfCommerce() {
        return noChamberOfCommerce;
    }

    public void setNoChamberOfCommerce(String noChamberOfCommerce) {
        this.noChamberOfCommerce = noChamberOfCommerce;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}