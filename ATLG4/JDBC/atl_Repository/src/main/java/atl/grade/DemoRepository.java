package atl.grade;

import atl.grade.config.ConfigManager;
import atl.grade.dto.GradeDto;
import atl.grade.dto.StudentDto;
import atl.grade.exception.RepositoryException;
import atl.grade.repository.StudentRepository;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author jlc
 */
public class DemoRepository {

    /**
     * Entry points to the <code> Mentoring </code> application.
     *
     * @param args no arguments needed.
     */
    public static void main(String[] args) {
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            StudentRepository repository = new StudentRepository();
            List<StudentDto> dtos = repository.getAll();
            for (StudentDto dto : dtos) {
                System.out.print("\t" + dto.getKey());
                System.out.print("\t" + dto.getFirstName());
                System.out.println("\t" + dto.getLastName());
                List<GradeDto> gradesDto = dto.getGrades();
                if (gradesDto == null || gradesDto.isEmpty()) {
                    System.out.println("\t\t Aucune évaluation encodée");
                } else {
                    for (GradeDto gradeDto : gradesDto) {
                        System.out.println("\t\t " + gradeDto.getKey());
                        System.out.println("\t\t " + gradeDto.getLesson());
                        System.out.println("\t\t " + gradeDto.getValue());
                    }
                }
                System.out.println("");
            }
        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        } catch (RepositoryException ex) {
            System.out.println("Erreur Repository " + ex.getMessage());
        }
    }

    private DemoRepository() {

    }
}
