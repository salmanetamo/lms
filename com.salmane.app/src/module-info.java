module com.salmane.app {
    requires transitive com.salmane.usermanagement;
    requires com.salmane.core;

    uses com.salmane.usermanagement.persistence.IUserPersistence;
}