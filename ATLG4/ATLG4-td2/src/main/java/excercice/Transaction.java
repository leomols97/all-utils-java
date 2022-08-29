/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excercice;

/**
 *
 * @author Leong Paeg-Hing
 */
public class Transaction extends Thread{
    
    private BankAccount account;
    private String r1;
    private String r2;
    
    public Transaction(String name, BankAccount account, String r1, String r2) {
        super(name);
        this.account = account;
        this.r1 = r1;
        this.r2 = r2;
    }
    
    public void run() {
        while(true) {
            System.out.println(getName());
            synchronized(this.r1) {
                this.account.deposit(1000);
                
                try {
                    System.out.println("sleep 100");
                sleep(100);
                } catch (InterruptedException e) {
                
            }
                
                synchronized(this.r2) {
                    this.account.withdraw(1000);
                    System.out.println(this.account.getBalance());
                }
            }
            
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                
            }
        }
    }
}
