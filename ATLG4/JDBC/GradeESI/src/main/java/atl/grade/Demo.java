package atl.grade;

/**
 *
 * @author jlc
 */
public abstract class Demo {

    public abstract void execute(String url);

    public abstract String getTitle();

    public void printTitle() {
        System.out.println("\n=====================================================");
        System.out.println("\t" + getTitle());
        System.out.println("=====================================================");
    }
}
