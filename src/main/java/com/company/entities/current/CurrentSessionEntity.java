package com.company.entities.current;

import com.company.entities.SessionEntity;

public class CurrentSessionEntity {
    private static SessionEntity session;

    public static SessionEntity getSession() {
        return session;
    }

    public static void setSession(SessionEntity session) {
        CurrentSessionEntity.session = session;
    }
}
