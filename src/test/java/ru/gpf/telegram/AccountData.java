package ru.gpf.telegram;

import ru.gpf.telegram.domain.Account;
import ru.gpf.telegram.domain.RegisteredAccount;
import ru.gpf.telegram.domain.RegisteredStatus;

public class AccountData {
    public static final Account ACCOUNT = new Account(UserData.USER_ID);
    public final static RegisteredAccount CREATE_ACCOUNT_SUCCESSFUL = new RegisteredAccount(RegisteredStatus.REGISTERED);
    public final static RegisteredAccount CREATE_ACCOUNT_UNSUCCESSFUL = new RegisteredAccount(RegisteredStatus.UNREGISTERED);
    public final static RegisteredAccount CREATE_ACCOUNT_CONFLICT = new RegisteredAccount(RegisteredStatus.CONFLICT_REGISTERED);
}
