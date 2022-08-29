package atl.grade;

import atl.grade.change.DemoDelete;
import atl.grade.change.DemoInsert;
import atl.grade.change.DemoUpdate;
import atl.grade.change.InsertDelete;
import atl.grade.config.ConfigManager;
import atl.grade.date.DemoDateSelect;
import atl.grade.date.InsertGrades;
import atl.grade.injection.DemoInjection;
import atl.grade.injection.DemoInjectionBis;
import atl.grade.join.DemoJoin;
import atl.grade.prepare.DemoPrepare;
import atl.grade.selection.DemoSelect;
import atl.grade.selection.DemoSelectAll;
import atl.grade.selection.SelectAllLesson;
import atl.grade.transaction.DemoTransaction;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jlc
 */
public class DemoJDBC {

    /**
     * Entry points to the <code> Mentoring </code> application.
     *
     * @param args no arguments needed.
     */
    public static void main(String[] args) {
        try {
            ConfigManager.getInstance().load();
            System.out.println("wow");
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            Demo demo = new DemoSelectAll();
            demo.printTitle();
            demo.execute(dbUrl);
          
//            Demo getLesson = new SelectAllLesson();
//            getLesson.printTitle();
//            getLesson.execute(dbUrl);

//            Demo testSelect = new DemoSelect();
//            testSelect.printTitle();
//            testSelect.execute(dbUrl);

//            Demo testUpdate = new DemoUpdate();
//            testUpdate.printTitle();
//            testUpdate.execute(dbUrl);

//            Demo testInsert = new DemoInsert();
//            testInsert.printTitle();
//            testInsert.execute(dbUrl);

//            Demo testDelete = new DemoDelete();
//            testDelete.printTitle();
//            testDelete.execute(dbUrl);

//            Demo testANLL = new InsertDelete();
//            testANLL.printTitle();
//            testANLL.execute(dbUrl);

//            Demo testDate = new DemoDateSelect();
//            testDate.printTitle();
//            testDate.execute(dbUrl);

//            Demo testGrades = new InsertGrades();
//            testGrades.printTitle();
//            testGrades.execute(dbUrl);

//            Demo testTransaction = new DemoTransaction();
//            testTransaction.printTitle();
//            testTransaction.execute(dbUrl);
            
//            Demo testJoin = new DemoJoin();
//            testJoin.printTitle();
//            testJoin.execute(dbUrl);

//            System.out.println("Entrez votre requête.");
//            Scanner input = new Scanner(System.in);
//            String query = input.nextLine();
//            Demo demo = new DemoInjection(query);
//            demo.printTitle();
//            demo.execute(dbUrl);
            
//            Scanner input = new Scanner(System.in);
//            System.out.println("Entrez votre requête.");
//            System.out.println("Id : ");
//            int id = input.nextInt();
//            input.nextLine();
//            System.out.println("Lastname : ");
//            String lastname = input.nextLine();
//            Demo demoInjectionBis = new DemoInjectionBis(dbUrl, id, lastname);
//            demoInjectionBis.printTitle();
//            demoInjectionBis.execute(dbUrl);
            
//            Scanner input = new Scanner(System.in);
//            System.out.println("Entrez votre requête.");
//            System.out.println("Id : ");
//            int id = input.nextInt();
//            input.nextLine();
//            System.out.println("Lastname : ");
//            String lastname = input.nextLine();
//            Demo demoPrepare = new DemoPrepare(dbUrl, id, lastname);
//            demoPrepare.printTitle();
//            demoPrepare.execute(dbUrl);
        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        }
    }

    private DemoJDBC() {

    }
}
