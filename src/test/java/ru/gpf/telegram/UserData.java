package ru.gpf.telegram;

import org.springframework.stereotype.Component;
import ru.gpf.telegram.domain.RegisteredStatus;
import ru.gpf.telegram.domain.RegisteredUser;
import ru.gpf.telegram.domain.User;

@Component
public class UserData {
    public final static Long USER_ID = 1L;
    public final static String USER_NAME = "durov_ton";

    public final static User USER = new User(USER_ID, USER_NAME);

    public final static RegisteredUser REGISTERED_USER_SUCCESSFUL = new RegisteredUser(RegisteredStatus.REGISTERED);
    public final static RegisteredUser REGISTERED_USER_UNSUCCESSFUL = new RegisteredUser(RegisteredStatus.UNREGISTERED);
    public final static RegisteredUser REGISTERED_USER_DUPLICATE = new RegisteredUser(RegisteredStatus.ALREADY_REGISTERED);


}
