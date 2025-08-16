package com.spring.kitties.model;

import jakarta.persistence.Table;
import jdk.jfr.Enabled;

@Enabled
@Table(name = "friends")
public class Friends {
    private String user_id;
    private String friend_id;
}
