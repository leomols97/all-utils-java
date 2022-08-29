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
public class BankAccount {
    
    private String name;
    private int balance;
    
    public BankAccount(String name, int initBalance) {
        this.name = name;
        this.balance = initBalance;
    }
    
    public void deposit(int amount) {
        this.balance += amount;
    }
    
    public void withdraw(int amount) {
        this.balance -= amount;
    }
    
    public int getBalance() {
        return this.balance;
    }
}
