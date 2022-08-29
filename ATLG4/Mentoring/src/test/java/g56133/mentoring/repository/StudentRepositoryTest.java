/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56133.mentoring.repository;

import g56133.atl.Mentoring.dto.StudentDto;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Leong Paeg-Hing
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class StudentRepositoryTest {
    
    @Mock
    private StudentDao mock;
    
    private final StudentDto bob;
    private final StudentDto patrick;
    private static final int KEY = 12345;
    private final List<StudentDto> all;
    
    public StudentRepositoryTest() {
        System.out.println("StudentRepositotry constructor.");
        //Test data
        bob = new StudentDto(KEY, "SquarePants", "SpongeBob");
        patrick = new StudentDto(99999, "Star", "Patrick");
        
        all = new ArrayList<>();
        all.add(bob);
        all.add(patrick);
    }
    
    @BeforeEach
    void init() throws RepositoryException {
        System.out.println("==== BEFORE EACH =====");
        //Mock behaviour
        Mockito.lenient().when(mock.get(bob.getKey())).thenReturn(bob);
        Mockito.lenient().when(mock.get(patrick.getKey())).thenReturn(null);
        Mockito.lenient().when(mock.getAll()).thenReturn(all);
        Mockito.lenient().when(mock.get(null)).thenThrow(RepositoryException.class);
    }
    
    @Test
    public void testGetExist() throws Exception {
        System.out.println("testGetExist");
        StudentRepository repoTest = new StudentRepository();
        StudentDto expected = bob;
        StudentRepository repository = new StudentRepository(mock);
        StudentDto result = repository.get(KEY);
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).get(KEY);
    }
    
    @Test
    public void testGetNotExist() throws Exception {
        System.out.println("testGetNotExist");
        StudentDto expected = null;
        StudentRepository repository = new StudentRepository(mock);
        StudentDto result = repository.get(99999);
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).get(99999);
    }
    
    @Test
    public void testGetIncorrectParameter() throws Exception {
        System.out.println("testGetIncorrectParameter");
        StudentRepository repository = new StudentRepository(mock);
        assertThrows(RepositoryException.class, () -> {
            repository.get(null);
        });
        Mockito.verify(mock, times(1)).get(null);
    }
    
    @Test
    public void testAddWhenExisting() throws Exception {
        System.out.println("testAddWhenExisting");
        StudentRepository repository = new StudentRepository(mock);
        repository.add(bob);
        Mockito.verify(mock, times(1)).get(KEY);
        Mockito.verify(mock, times(1)).update(bob);
        Mockito.verify(mock, times(0)).insert(any(StudentDto.class));
    }
    
    @Test
    public void testAddWhenNotExisting() throws Exception {
        System.out.println("testAddWhenNotExisting");
        StudentRepository repository = new StudentRepository(mock);
        repository.add(patrick);
        Mockito.verify(mock, times(1)).get(patrick.getKey());
        Mockito.verify(mock, times(0)).update(any(StudentDto.class));
        Mockito.verify(mock, times(1)).insert(patrick);
    }
    
    @Test
    public void testAddWhenIncorrectparameter() throws Exception {
        System.out.println("testAddWhenIncorrectparameter");
        StudentRepository repository = new StudentRepository(mock);
        assertThrows(RepositoryException.class, () -> {
            repository.add(null);
        });
        Mockito.verify(mock, times(0)).get(null);
        Mockito.verify(mock, times(0)).update(any(StudentDto.class));
        Mockito.verify(mock, times(0)).insert(any(StudentDto.class));
    }
    
    @Test
    public void testRemoveWhenNotExist() throws Exception {
        System.out.println("testRemoveWhenNotExist");
        StudentRepository repository = new StudentRepository(mock);
        Integer tmpKey = 78512;
        assertThrows(RepositoryException.class, () -> {
            repository.remove(tmpKey);
        });
        Mockito.verify(mock, times(0)).delete(tmpKey);
    }
    
    @Test
    public void testRemoveWhenIncorrect() throws Exception {
        System.out.println("testRemoveWhenIncorrect");
        StudentRepository repository = new StudentRepository(mock);
        assertThrows(RepositoryException.class, () -> {
            repository.remove(null);
        });
        Mockito.verify(mock, times(0)).delete(null);
    }
    
    @Test
    public void testGetAllWhenExist() throws Exception {
        System.out.println("testGetAllWhenExist");
        StudentRepository repository = new StudentRepository(mock);
        List<StudentDto> l = repository.getAll();
        Mockito.verify(mock, times(1)).getAll();
    }
    
    @Test
    public void testContainWhenExist() throws Exception {
        System.out.println("testContainWhenExist");
        StudentRepository repository = new StudentRepository(mock);
        boolean result = repository.contains(KEY);
        assertEquals(true, result);
        Mockito.verify(mock, times(1)).get(KEY);
    }
    
    @Test
    public void testContainWhenNotExist() throws Exception {
        System.out.println("testContainWhenNotExist");
        StudentRepository repository = new StudentRepository(mock);
        boolean result = repository.contains(78962);
        assertEquals(false, result);
        Mockito.verify(mock, times(1)).get(78962);
    }
    
    @Test
    public void testContainIncorrectParameter() throws Exception {
        System.out.println("testContainIncorrectParameter");
        StudentRepository repository = new StudentRepository(mock);
        assertThrows(RepositoryException.class, () -> {
            repository.contains(null);
        });
        Mockito.verify(mock, times(1)).get(null);
    }
}
