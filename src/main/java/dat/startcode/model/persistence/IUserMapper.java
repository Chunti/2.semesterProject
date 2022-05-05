package dat.startcode.model.persistence;

import dat.startcode.model.entities.User;
import dat.startcode.model.exceptions.DatabaseException;

public interface IUserMapper
{
    User login(String email, String kodeord) throws DatabaseException;
    User createUser(String username, String password, String role) throws DatabaseException;
}
