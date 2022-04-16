/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.common;

/**
 *
 * @author Leong Paeg-Hing
 */
public class NonReplayableMessage extends BasicMessage {
    private int challenge;
    
    public NonReplayableMessage(int challenge, MsgType msg) {
        super(msg);
        this.challenge = challenge;
    }
    
    public int getChallenge() {
        return this.challenge;
    }
}
