package sec.server;

import sec.common.BasicMessage;
import sec.common.MsgType;
import sec.common.TextMessage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import sec.client.BasicTextClient;

public class ServerMain extends BasicServer
{
    private final UserDB userDB;
    private final PublicKey publicKey; 
    private final PrivateKey privateKey;
    //private SecretKey secretKey;
    private PublicKey publicKeyClient;
    private ArrayList<String> sessions;

    public ServerMain(int listeningPort, String filePath) throws IOException, NoSuchAlgorithmException
    {
        super(listeningPort);
        userDB = new UserDB(filePath);
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secure = new SecureRandom();
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();
        sessions = new ArrayList<>();
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
            String txt = decipher(msg.getText());
            System.out.println(txt);
            String[] data = txt.split(":");
//            for(String o : data)
//                System.out.println(o);
            
            // Check if the current user is authenticated or not
            if(sessions.contains(data[1])) {
                if (data[0]
                   .equalsIgnoreCase("You killed my father"))
                {
                    out.writeObject(encryption("No, I am your father"));
                }
                else {
                    out.writeObject(encryption("Whatever"));
                }
            }else 
                out.writeObject(encryption("This command can't be executed "
                        + "because you are not authenficated"));
            
        });

        //handler 2
        registerHandler(MsgType.HELLO, (message, in, out) ->
        {

            TextMessage msg = (TextMessage) message;
            String txt = decipher(msg.getText());
            if (txt.equalsIgnoreCase("Hello there"))
            {
                out.writeObject(encryption("General Kenobi!"));
            }
            else
            {
                out.writeObject(encryption("Whatever"));
            }
        });
        
        // Handler to get the public key of the client and send the server PK to the client
        registerHandler(MsgType.CONNECTION, (message, in, out) ->
        {

            TextMessage msg = (TextMessage) message;
            //System.out.println("Client PK : " + msg.getText());
            byte[] publicK = Base64.getDecoder().decode(msg.getText());
            
            // Key factor to get the clientPK
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
            
            // Send the server Pk to the client
            String serverPk = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            //System.out.println("hehe boy : " + serverPk);
            out.writeObject(serverPk);
        });
        
        // Register a new user
        registerHandler(MsgType.REGISTER, (message, in, out) ->
        {
            TextMessage msg = (TextMessage) message;
            //System.out.println("Before : " + msg.getText());
            
            String a = decipher(msg.getText());
            //System.out.println("After : " + a);
            
            String[] data = a.split(" ");
//            for(String s : data)
//                System.out.println(s);
            
            // Check the register information
            if(data.length < 2)
                out.writeObject(encryption("There are fields "
                        + "that are missing."));
            else if(userDB.isRegistered(data[0]))
                out.writeObject(encryption("The login is "
                        + "already used."));
            else if(data[1].length() < 6 || data[1].length() > 8 || 
                    !Charset.forName("US-ASCII").newEncoder().canEncode(data[1]))
                out.writeObject(encryption("The password "
                        + "must be between 6 and 8 ASCII characters"));
            else {
                // Hash the password
                byte[] salt = new byte[16];
                SecureRandom random = new SecureRandom();
                random.nextBytes(salt);
                byte[] digestPassword = passwordHashing(data[1], salt);
            
                // Register the user
                UserDB.User newUser = new UserDB.User(data[0], digestPassword, salt);
                if(userDB.add(newUser))
                    out.writeObject(encryption("Succesfully register"
                            + "\nwelcome new user"));
                else 
                    out.writeObject(encryption("The login is already used."));
            }
        });
        
        // Logged a user
        registerHandler(MsgType.LOGIN, (message, in, out) ->
        {
            TextMessage msg = (TextMessage) message;
            //System.out.println("Before : " + msg.getText());
            
            String a = decipher(msg.getText());
            //System.out.println("After : " + a);
            
            String[] data = a.split(" ");
//            for(String s : data)
//                System.out.println(s);
            
            // Check the register information
            if(data.length < 2)
                out.writeObject(encryption("There are fields that are missing."));
            else {
                UserDB.User user = userDB.get(data[0]);
                byte[] digestPassword = passwordHashing(data[1], user.getField(1));
                String pwd1 = Base64.getEncoder().encodeToString(digestPassword);
                String pwd2 = Base64.getEncoder().encodeToString(user.getField(0));
                if(pwd1.equals(pwd2)) {
                    String sessionId = UUID.randomUUID().toString();
                    sessions.add(sessionId);
                    //System.out.println("session : " + sessionId);
                    out.writeObject(encryption("Succefully authenticated.:" + sessionId));
                }else
                    out.writeObject(encryption("Wrong password."));
            }
        });
        
        System.out.println("Handlers registered");
    }
    
    private String encryption(String textData) {
        Cipher cipher;
        String encodedTextData = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKeyClient);
            byte[] textDataBytes = textData.getBytes(StandardCharsets.UTF_8);
            byte[] encryptedTextDataBytes = cipher.doFinal(textDataBytes);
            encodedTextData = Base64.getEncoder().encodeToString(encryptedTextDataBytes);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(BasicTextClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(BasicTextClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(BasicTextClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(BasicTextClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(BasicTextClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encodedTextData;
    }
    
    private String decipher(String textData) {
        Cipher cipher;
        String decipheredTextData = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] secretMessage = Base64.getDecoder().decode(textData);
            byte[] decipheredTextDataBytes = cipher.doFinal(secretMessage);
            decipheredTextData = new String(decipheredTextDataBytes, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(BasicTextClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(BasicTextClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(BasicTextClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(BasicTextClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return decipheredTextData;
    }
    
    private byte[] passwordHashing(String plainPassword, byte[] salt) {
        byte[] digestPassword = null;
        try {
            // Hashing the password with the salt
            KeySpec spec = new PBEKeySpec(plainPassword.toCharArray(), salt, 100, 512);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            digestPassword = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digestPassword;
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
