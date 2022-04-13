package sec.client;

import java.security.NoSuchAlgorithmException;

public class ClientMain
{
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        new BasicTextClient("localhost", 42000).start();
    }
}
