package sec.common;

public enum MsgType
{
    EXIT,
    FATHER,
    HELLO,
    CONNECTION, // To get the public key of the server
    REGISTER, // Create a new user
    LOGIN, // To log to the server
    TEST
}
