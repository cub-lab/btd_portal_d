package ro.cub.btddigitalportals.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "CUB_CLIENT", indexes = {
        @Index(name = "IDX_CUB_CLIENT_INDIVIDUAL_CLIENT", columnList = "INDIVIDUAL_CLIENT_ID"),
        @Index(name = "IDX_CUB_CLIENT_LEGAL_CLIENT", columnList = "LEGAL_CLIENT_ID")
})
@Entity(name = "cub_Client")
public class Client {
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

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private OffsetDateTime lastModifiedDate;

    @Column(name = "CLIENT_TYPE")
    private String clientType;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "INDIVIDUAL_CLIENT_ID")
    @Composition
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private IndividualClient individualClient;

    @OnDeleteInverse(DeletePolicy.DENY)
    @JoinColumn(name = "LEGAL_CLIENT_ID")
    @Composition
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private LegalClient legalClient;

    public LegalClient getLegalClient() {
        return legalClient;
    }

    public void setLegalClient(LegalClient legalClient) {
        this.legalClient = legalClient;
    }

    public IndividualClient getIndividualClient() {
        return individualClient;
    }

    public void setIndividualClient(IndividualClient individualClient) {
        this.individualClient = individualClient;
    }

    public ClientType getClientType() {
        return clientType == null ? null : ClientType.fromId(clientType);
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType == null ? null : clientType.getId();
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}