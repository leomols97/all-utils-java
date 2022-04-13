package sec.server;

import sec.common.BasicMessage;
import sec.common.MsgType;
import sec.common.TextMessage;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ServerMain extends BasicServer
{
    private final UserDB userDB;
    private final PublicKey publicKey; 
    private final PrivateKey privateKey;
    private SecretKey secretKey;
    private PublicKey publicKeyClient;

    public ServerMain(int listeningPort, String filePath) throws IOException, NoSuchAlgorithmException
    {
        super(listeningPort);
        userDB = new UserDB(filePath);
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secure = new SecureRandom();
        generator.initialize(2048, secure);
        KeyPair pair = generator.generateKeyPair();
        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();
//        String publicK = Base64.getEncoder().encodeToString(publicKey.getEncoded());
//        String privateK = Base64.getEncoder().encodeToString(privateKey.getEncoded());
//        System.out.println("Public key");
//        System.out.println(publicK);
//        System.out.println(privateK);
        registerHandlers();
    }

    @Override
    public void start() throws IOException
    {
        // Example usage of UserDB -->
        // Create an user.
        byte[] password = "password".getBytes();
        byte[] otherData = {0, 42, 21};
        UserDB.User admin = new UserDB.User("admin", password, otherData);

        System.out.println("admin user in DB: " + userDB.isRegistered("admin"));

        // Add (if not already present) the user to the database.
        if (userDB.add(admin))
        {
            System.out.println("Added dummy admin user");
        }

        // Fetch it back and display data.
        UserDB.User user = userDB.get("admin");
        String readPassword = new String(user.getField(0));
        System.out.println(
                "User " + user + " has password "
                + readPassword + " and otherData: " +
                Arrays.toString(user.getField(1)));
        // <-- Example

        super.start();
    }

    private void registerHandlers()
    {
        //handler 1
        registerHandler(MsgType.FATHER, (message, in, out) ->
        {

            TextMessage msg = (TextMessage) message;

            if (msg.getText()
                   .equalsIgnoreCase("You killed my father"))
            {
                out.writeObject("No, I am your father");
            }
            else
            {
                out.writeObject("Whatever");
            }
        });

        //handler 2
        registerHandler(MsgType.HELLO, (message, in, out) ->
        {

            TextMessage msg = (TextMessage) message;

            if (msg.getText().equalsIgnoreCase("Hello there"))
            {
                out.writeObject("General Kenobi!");
            }
            else
            {
                out.writeObject("Whatever");
            }
        });
        
        // Handler to send the public key to the client
        registerHandler(MsgType.PUBLIC, (message, in, out) ->
        {

            TextMessage msg = (TextMessage) message;

            String publicK = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            out.writeObject(publicK);
        });
        
        // Handler to receive the session key send by the client
        registerHandler(MsgType.SESSION, (message, in, out) ->
        {

            TextMessage msg = (TextMessage) message;
            //System.out.println("session key before decipher : " + msg.getText());
            
            byte[] decodedCipherKey = Base64.getDecoder().decode(msg.getText());
            
            
            Cipher cipher;
            try {
                cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
                cipher.update(decodedCipherKey);
                byte[] decipherKey = cipher.doFinal();
                secretKey = new SecretKeySpec(decipherKey, 0, decipherKey.length, "AES");
                //String sessionKey = Base64.getEncoder().encodeToString(cipherKey);
                //System.out.println("Session key after decipher : " + sessionKey);
            } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
                Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        // Handler to receive the public key of the client
        registerHandler(MsgType.SEND, (message, in, out) ->
        {

            TextMessage msg = (TextMessage) message;
            
            String publicKString = msg.getText();
            //System.out.println("Client key : " + publicKString);
            byte[] publicK = Base64.getDecoder().decode(publicKString);
            
            // Key factor
            KeyFactory keyFactory;
            try {
                keyFactory = KeyFactory.getInstance("RSA");
                X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicK);
                publicKeyClient = keyFactory.generatePublic(publicKeySpec);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        });

        System.out.println("Handlers registered");
    }

    @Override
    public void close() throws Exception
    {
        super.close();
        userDB.close();
    }

    public static void main(String[] args)
    {
        try (ServerMain server = new ServerMain(42000, "userdb.txt"))
        {
            server.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
