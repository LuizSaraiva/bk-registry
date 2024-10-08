package com.bk.registry.api.enums.account;

public enum StatusAccountApi {
    ACTIVE,
    INACTIVE;

    public static boolean contains(String status) {
        for (StatusAccountApi s : StatusAccountApi.values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }
}
