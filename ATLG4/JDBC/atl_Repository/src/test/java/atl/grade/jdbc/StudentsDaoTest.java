package atl.grade.jdbc;

import atl.grade.config.ConfigManager;
import atl.grade.dto.StudentDto;
import atl.grade.exception.RepositoryException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author jlc
 */
public class StudentsDaoTest {

    private final StudentDto bob;
    private final StudentDto patrick;

    private static final int KEY = 6;

    private final List<StudentDto> all;

    private StudentsDao instance;

    public StudentsDaoTest() {
        System.out.println("==== StudentDaoTest Constructor =====");
        bob = new StudentDto(KEY, "SquarePants", "SpongeBob");
        patrick = new StudentDto(99_999, "Star", "Patrick");

        all = new ArrayList<>();
        all.add(new StudentDto(1, "Olsen", "Maggy"));
        all.add(new StudentDto(2, "Frost", "Phoebe"));
        all.add(new StudentDto(3, "Ortega", "Skyler"));
        all.add(new StudentDto(4, "Blankenship", "Byron"));
        all.add(new StudentDto(5, "Cote", "Molly"));
        all.add(bob);

        try {
            ConfigManager.getInstance().load();
            instance = StudentsDao.getInstance();
        } catch (RepositoryException | IOException ex) {
            org.junit.jupiter.api.Assertions.fail("Erreur de connection à la base de données de test", ex);
        }
    }

    @Test
    public void testSelectExist() throws Exception {
        System.out.println("testSelectExist");
        //Arrange
        StudentDto expected = bob;
        //Action
        StudentDto result = instance.select(KEY);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testSelectNotExist() throws Exception {
        System.out.println("testSelectNotExist");
        //Arrange
        //Action
        StudentDto result = instance.select(patrick.getKey());
        //Assert
        assertNull(result);
    }

    @Test
    public void testSelectIncorrectParameter() throws Exception {
        System.out.println("testSelectIncorrectParameter");
        //Arrange
        Integer incorrect = null;
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            instance.select(incorrect);
        });
    }
    
    @Test
    public void testGetFullExist() throws Exception {
        System.out.println("testSelectExist");
        //Arrange
        StudentDto expected = all.get(0);
        //Action
        StudentDto result = instance.getFullStudent(1);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testGetFullNotExist() throws Exception {
        System.out.println("testSelectNotExist");
        //Arrange
        //Action
        StudentDto result = instance.getFullStudent(patrick.getKey());
        //Assert
        assertNull(result);
    }

    @Test
    public void testGetFullIncorrectParameter() throws Exception {
        System.out.println("testSelectIncorrectParameter");
        //Arrange
        Integer incorrect = null;
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            instance.getFullStudent(incorrect);
        });
    }

}
