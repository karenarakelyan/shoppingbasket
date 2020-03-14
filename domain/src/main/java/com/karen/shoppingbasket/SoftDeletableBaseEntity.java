package com.karen.shoppingbasket;

import com.karen.shoppingbasket.entity.ActionTracesAwareBaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author Karen Arakelyan
 */
@MappedSuperclass
public class SoftDeletableBaseEntity extends ActionTracesAwareBaseEntity {

    @Column(name = "deletedon")
    private LocalDateTime deletedOn;

    public LocalDateTime getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(final LocalDateTime deletedOn) {
        this.deletedOn = deletedOn;
    }
}
