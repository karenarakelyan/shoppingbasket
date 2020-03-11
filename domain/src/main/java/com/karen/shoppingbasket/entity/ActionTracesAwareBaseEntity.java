package com.karen.shoppingbasket.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * @author Karen Arakelyan
 */

public class ActionTracesAwareBaseEntity extends BaseEntity {

    @Column(name = "createdon", nullable = false, updatable = false)
    private LocalDateTime createdOn;

    @Column(name = "updatedon")
    private LocalDateTime updatedOn;

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(final LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(final LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("createdOn", createdOn)
                .append("updatedOn", updatedOn)
                .toString();
    }

}
