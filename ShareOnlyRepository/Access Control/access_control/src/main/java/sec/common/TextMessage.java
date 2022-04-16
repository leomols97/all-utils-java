package sec.common;

import java.io.Serializable;

public class TextMessage extends BasicMessage implements Serializable
{
    private final String txt;
    private String challenge;
    private String response;

    public TextMessage(String txt, MsgType type, String challenge, String response)
    {
        super(type);
        this.txt = txt;
        this.challenge = challenge;
        this.response = response;
    }

    public String getText()
    {
        return txt;
    }
    
    public String getChallenge() {
        return challenge;
    }
    
    public String getResponse() {
        return response;
    }
}
