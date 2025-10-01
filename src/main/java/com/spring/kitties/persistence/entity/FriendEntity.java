package com.spring.kitties.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "friend")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FriendEntity {
    @EmbeddedId
    private FriendKey friendKey;
    private boolean accepted;

    public String getUserId() {
        return friendKey.getUserId();
    }
    public String getFriendId() {return friendKey.getFriendId();}
}
