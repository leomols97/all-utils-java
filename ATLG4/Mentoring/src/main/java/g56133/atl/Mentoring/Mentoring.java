package g56133.atl.Mentoring;

import g56133.atl.Mentoring.config.ConfigManager;
import g56133.atl.Mentoring.dto.StudentDto;
import g56133.mentoring.repository.RepositoryException;
import g56133.mentoring.repository.StudentRepository;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Leong Paeg-Hing
 */
public class Mentoring {

    public Mentoring() {
    }

    public static void main(String[] args) {
        System.out.println("Hello world !");
        Mentoring m = new Mentoring();
        m.checkPath();
    }

    public void checkPath() {
        System.out.println("Chemin courant \t" + new File(".")
                .getAbsolutePath()); //le répertoire de votre projet sur la machine
        System.out.println("Chemin classe \t"
                + this.getClass().getResource(".").getPath()); //  le répertoire de la classe en cours d’exécution
        System.out.println("Chemin jar \t" + new File(getClass()
                .getClassLoader().getResource(".").getFile())); // le répertoire de l’exécutable
    }
}
