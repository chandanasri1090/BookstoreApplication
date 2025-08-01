package com.example.bookstore.models;

import java.util.Set;

public enum Role {

    ADMIN(Set.of(Permission.BOOK_READ,Permission.BOOK_WRITE,Permission.BOOK_DELETE)),
    USER(Set.of(Permission.BOOK_READ));

    Set<Permission>permissions;

    private Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    
    
}
