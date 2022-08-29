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
public class TestTransaction {
    
    public static void main(String[] args) {
        BankAccount account = new BankAccount("marty", 10000);
    String r1 = "depot";
    String r2 = "retrait";
    Transaction t1 = new Transaction("t1", account, r1, r2);
    Transaction t2 = new Transaction("t2", account, r1, r2);
    Transaction t3 = new Transaction("t3", account, r1, r2);
    Transaction t4 = new Transaction("t4", account, r1, r2);
    Transaction t5 = new Transaction("t5", account, r1, r2);
    Transaction t6 = new Transaction("t6", account, r1, r2);
    Transaction t7 = new Transaction("t7", account, r1, r2);
    Transaction t8 = new Transaction("t8", account, r1, r2);
    Transaction t9 = new Transaction("t9", account, r1, r2);
    Transaction t10 = new Transaction("t10", account, r1, r2);
    Transaction t11 = new Transaction("t11", account, r1, r2);
    Transaction t12 = new Transaction("t12", account, r1, r2);
    Transaction t13 = new Transaction("t13", account, r1, r2);
    Transaction t14 = new Transaction("t14", account, r1, r2);
    Transaction t15 = new Transaction("t15", account, r1, r2);
    Transaction t16 = new Transaction("t16", account, r1, r2);
    Transaction t17 = new Transaction("t17", account, r1, r2);
    Transaction t18 = new Transaction("t18", account, r1, r2);
    Transaction t19 = new Transaction("t19", account, r1, r2);
    Transaction t20 = new Transaction("t20", account, r1, r2);
    
    t1.start();
    t2.start();
    t3.start();
    t4.start();
    t5.start();
    t6.start();
    t7.start();
    t8.start();
    t9.start();
    t10.start();
    t11.start();
    t12.start();
    t13.start();
    t14.start();
    t15.start();
    t16.start();
    t17.start();
    t18.start();
    t19.start();
    t20.start();
    
    
    
    
    
    }
}
