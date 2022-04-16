package sec.client;

import sec.common.BasicMessage;
import sec.common.MsgType;
import sec.common.TextMessage;

import java.io.EOFException;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.*;
import java.util.Base64;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import sec.server.ServerMain;

public class BasicTextClient
{
    String ip;
    int port;
    private final PublicKey publicKey; 
    private final PrivateKey privateKey;
    private PublicKey serverPK;
    private String session;
    private SecureRandom secure;
    private Integer challenge;
    private Integer response;

    public BasicTextClient(String ip, int port) throws NoSuchAlgorithmException
    {
        this.ip = ip;
        this.port = port;
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        //SecureRandom secure = new SecureRandom();
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();
        session = null;
        secure = new SecureRandom();
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
            
            // Send client PK to the server.
            String clientPK = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            //System.out.println("PK : " + clientPK);
            toServer.writeObject(new TextMessage(clientPK, MsgType.CONNECTION, "", ""));
            
            // Get the PK of the server
            TextMessage message = (TextMessage) fromServer.readObject();
            //System.out.println("Server PK : " + sKey);
            byte[] publicK = Base64.getDecoder().decode(message.getText());
            
            // save server challenge
            String decipherChallenge = decipher(message.getChallenge());
            response = Integer.parseInt(decipherChallenge);
            System.out.println("After connection : " + response);
            
            // Key factor to get the serverPk
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicK);
            serverPK = keyFactory.generatePublic(publicKeySpec);
            
            // End of key exchange
            System.out.print("> ");
            while (scanner.hasNextLine() &&
                   !(line = scanner.nextLine()).equalsIgnoreCase("exit"))
            {
                try
                {
                    int sepIdx = line.indexOf(' ');
                    
                    // Prepare the challenge and the response
                    challenge = secure.nextInt();
                    String EncryptedChallenge = encryption(challenge.toString());
                    this.response = this.response + 1;
                    String encryptedResponse = encryption(response.toString());
//                    System.out.println("Response : " + response);
//                    System.out.println("Challenge : " + challenge);
                    
                    MsgType msgType = MsgType.valueOf(
                            line.substring(0, sepIdx).toUpperCase());
                    String textData = line.substring(sepIdx + 1);
                    if(MsgType.REGISTER == msgType || MsgType.LOGIN == msgType) {
                        textData = encryption(textData);
                    }else if(MsgType.FATHER == msgType) {
                        textData = encryption(textData+":"+session);
                    }
                    
                    // Send the message to the server
                    toServer.writeObject(new TextMessage(textData, msgType, EncryptedChallenge, encryptedResponse));
                    toServer.flush();
                    
                    // Check the response provided by the server
                    TextMessage serverMessage = (TextMessage) fromServer.readObject();
                    Integer serverResponse = Integer.parseInt(decipher(serverMessage.getResponse()));
                    if(!Objects.equals(serverResponse, this.challenge + 1)){
                        System.out.println("damn");
                    }
                    this.response = Integer.parseInt(decipher(serverMessage.getChallenge()));
                    
                    // If the checking is ok then the message of the server is readed
                    String decipheredMessage = decipher(serverMessage.getText());
                    if(MsgType.LOGIN == msgType) {
                        String[] splited = decipheredMessage.split(":");
                        decipheredMessage = splited[0];
                        session = splited[1];
                    }
                    System.out.println(decipheredMessage);
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
    
    private String encryption(String textData) {
        Cipher cipher;
        String encodedTextData = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, serverPK);
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
        javax.crypto.Cipher cipher;
        String decipheredTextData = null;
        try {
            cipher = javax.crypto.Cipher.getInstance("RSA");
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, privateKey);
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
}