package sec.client;

import sec.common.BasicMessage;
import sec.common.MsgType;
import sec.common.TextMessage;

import java.io.EOFException;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.*;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class BasicTextClient
{
    String ip;
    int port;
    private final PublicKey publicKey; 
    private final PrivateKey privateKey;

    public BasicTextClient(String ip, int port) throws NoSuchAlgorithmException
    {
        this.ip = ip;
        this.port = port;
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
    }

    public void start()
    {
        try (Socket socket = new Socket(ip, port);
             ObjectOutputStream toServer =
                     new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream fromServer =
                     new ObjectInputStream(socket.getInputStream()))
        {

            Scanner scanner = new Scanner(System.in);
            String line;
            // Mettre ca dans une méthode d'échange
            
            // Get the PK of the server
            toServer.writeObject(new TextMessage("", MsgType.PUBLIC));
            String publicKString = (String) fromServer.readObject();
            byte[] publicK = Base64.getDecoder().decode(publicKString);
            
            // Key factor
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicK);
            PublicKey serverPK = keyFactory.generatePublic(publicKeySpec);
            
            // Generate session key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey originalKey = keyGenerator.generateKey();
            //String sessionKeyS = Base64.getEncoder().encodeToString(originalKey.getEncoded());
            //System.out.println("Before cipher session key : " + sessionKeyS);
            
            // cipher the secret key
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, serverPK);
            cipher.update(originalKey.getEncoded());
            byte[] cipherKey = cipher.doFinal();
            String sessionKey = Base64.getEncoder().encodeToString(cipherKey);
            //System.out.println("Session key : " + sessionKey);
            
            // Send secret key to the server
            toServer.writeObject(new TextMessage(sessionKey, MsgType.SESSION));
            
            //Send client public key to the server
            String clientPublicK = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            toServer.writeObject(new TextMessage(clientPublicK, MsgType.SEND));
            
            // fin des méthode echange
            System.out.print("> ");
            while (scanner.hasNextLine() &&
                   !(line = scanner.nextLine()).equalsIgnoreCase("exit"))
            {
                try
                {
                    int sepIdx = line.indexOf(' ');
                    MsgType msgType = MsgType.valueOf(
                            line.substring(0, sepIdx).toUpperCase());
                    String textData = line.substring(sepIdx + 1);
                    toServer.writeObject(new TextMessage(textData, msgType));
                    toServer.flush();
                    //in.readObject();
                    System.out.println((String) fromServer.readObject());
                }
                catch (IllegalArgumentException | IndexOutOfBoundsException ex)
                {
                    System.out.println("Unknown command");
                }
                System.out.print("> ");
            }
            System.out.println("Graceful exit");
            toServer.writeObject(BasicMessage.EXIT_MSG);
        }
        catch (EOFException ex)
        {
            System.err.println("The server unexpectedly closed the connection");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}