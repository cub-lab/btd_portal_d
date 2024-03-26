package ro.cub.btddigitalportals.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

@JmixEntity(name = "cub_ConsumptionDeviceDTO")
public class ConsumptionDeviceDTO {
    @JmixGeneratedValue
    @JmixId
    private UUID id;

    @JmixProperty(mandatory = true)
    @NotNull
    @InstanceName
    private String name;

    @JmixProperty(mandatory = true)
    @NotNull
    @Positive(message = "{msg://ro.cub.btddigitalportals.entity/ConsumptionDeviceDTO.numberOfUserDevices.validation.Positive}")
    @Min(message = "{msg://ro.cub.btddigitalportals.entity/ConsumptionDeviceDTO.numberOfUserDevices.validation.Min}", value = 1)
    private Short numberOfUserDevices;

    @JmixProperty(mandatory = true)
    @NotNull
    private Double consumptionRate;

    @JmixProperty(mandatory = true)
    @NotNull
    private String type;

    public ConsumptionDeviceType getType() {
        return type == null ? null : ConsumptionDeviceType.fromId(type);
    }

    public void setType(ConsumptionDeviceType type) {
        this.type = type == null ? null : type.getId();
    }

    public Double getConsumptionRate() {
        return consumptionRate;
    }

    public void setConsumptionRate(Double consumptionRate) {
        this.consumptionRate = consumptionRate;
    }

    public Short getNumberOfUserDevices() {
        return numberOfUserDevices;
    }

    public void setNumberOfUserDevices(Short numberOfUserDevices) {
        this.numberOfUserDevices = numberOfUserDevices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}