package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;

import junit.framework.TestCase;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

public class RoleStoreTest implements WithAssertions {

    @Test
    void whenAddAndFindThenRoleNameIsAdministrator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("administrator");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        Role result = store.findById("8");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsAdministrator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.add(new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Administrator");
    }

    @Test
    void whenReplaceThenRoleNameIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.replace("1", new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("User");
    }

    @Test
    void whenNoReplaceUserThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.replace("10", new Role("10", "User"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Administrator");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsAdministrator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("Administrator");
    }
}