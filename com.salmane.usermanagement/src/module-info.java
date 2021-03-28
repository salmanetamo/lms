module com.salmane.usermanagement {
    requires com.salmane.core;
    exports com.salmane.usermanagement to com.salmane.app;
    exports com.salmane.usermanagement.persistence to com.salmane.db, com.salmane.app;
    exports com.salmane.usermanagement.model;
}