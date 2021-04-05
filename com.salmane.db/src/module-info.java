import com.salmane.db.usermanagement.UserRepository;
import com.salmane.db.usermanagement.RoleRepository;

module com.salmane.db {
    requires java.sql;
    requires com.salmane.usermanagement;
    requires com.salmane.core;

    provides com.salmane.usermanagement.persistence.IUserPersistence with UserRepository;
    provides com.salmane.usermanagement.persistence.IRolePersistence with RoleRepository;
}