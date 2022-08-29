package atl.grade.repository;

import atl.grade.dto.StudentDto;
import atl.grade.exception.RepositoryException;
import atl.grade.jdbc.StudentsDao;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author jlc
 */
/**
 *
 * @author jlc
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class StudentRepositoryTest {

    @Mock
    private StudentsDao mock;

    private final StudentDto bob;

    private final StudentDto patrick;

    private static final int KEY = 12_345;

    private final List<StudentDto> all;

    public StudentRepositoryTest() {
        System.out.println("StudentRepositoryTest Constructor");
        //Test data
        bob = new StudentDto(KEY, "SquarePants", "SpongeBob");
        patrick = new StudentDto(99_999, "Star", "Patrick");

        all = new ArrayList<>();
        all.add(bob);
        all.add(patrick);
    }

    @BeforeEach
    void init() throws RepositoryException {
        System.out.println("==== BEFORE EACH =====");
        //Mock behaviour
        Mockito.lenient().when(mock.select(bob.getKey())).thenReturn(bob);
        Mockito.lenient().when(mock.select(patrick.getKey())).thenReturn(null);
        Mockito.lenient().when(mock.selectAll()).thenReturn(all);
        Mockito.lenient().when(mock.select(null)).thenThrow(RepositoryException.class);
    }

    @Test
    public void testGetExist() throws Exception {
        System.out.println("testGetExist");
        //Arrange
        StudentDto expected = bob;
        StudentRepository repository = new StudentRepository(mock);
        //Action
        StudentDto result = repository.get(KEY);
        //Assert        
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).select(KEY);
    }

    @Test
    public void testGetNotExist() throws Exception {
        System.out.println("testGetNotExist");
        //Arrange
        StudentRepository repository = new StudentRepository(mock);
        //Action
        StudentDto result = repository.get(patrick.getKey());
        //Assert        
        assertNull(result);
        Mockito.verify(mock, times(1)).select(patrick.getKey());
    }

    @Test
    public void testGetIncorrectParameter() throws Exception {
        System.out.println("testGetIncorrectParameter");
        //Arrange
        StudentRepository repository = new StudentRepository(mock);
        Integer incorrect = null;
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            repository.get(incorrect);
        });
        Mockito.verify(mock, times(1)).select(null);
    }
    
    @Test
    public void testAddWhenExisting() throws Exception {
        System.out.println("testAddWhenExisting");
        //Arrange
        StudentRepository repository = new StudentRepository(mock);
        //Action
        repository.add(bob);
        //Assert        
        Mockito.verify(mock, times(1)).select(KEY);
        Mockito.verify(mock, times(1)).update(bob);
        Mockito.verify(mock, times(0)).insert(any(StudentDto.class));
    }    
}
