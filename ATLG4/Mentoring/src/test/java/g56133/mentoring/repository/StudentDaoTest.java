/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g56133.mentoring.repository;

import g56133.atl.Mentoring.dto.StudentDto;
import java.net.URLDecoder;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author Leong Paeg-Hing
 */
public class StudentDaoTest {
    
    private final StudentDto bob;
    private final StudentDto patrick;

    private static final int KEY = 12345;
    private static final String FILE_URL = "/data/test_repo_students.txt"; // pas besoin de classLoader donc

    private final String url;

    private final List<StudentDto> all;

    public StudentDaoTest() {
        System.out.println("==== StudentDaoTest Constructor =====");
        bob = new StudentDto(KEY, "SquarePants", "SpongeBob");
        patrick = new StudentDto(99999, "Star", "Patrick");

        all = List.of(new StudentDto(64931, "Olsen", "Maggy"),
                new StudentDto(73780, "Frost", "Phoebe"),
                new StudentDto(94853, "Ortega", "Skyler"),
                new StudentDto(93371, "Blankenship", "Byron"),
                new StudentDto(82227, "Cote", "Molly"),
                bob);
        
        String path = getClass().getResource(FILE_URL).getFile();
        path = URLDecoder.decode(path); // Pour regler les problemes d'espace dans le chemin
        this.url = path;
    }

//    @Test
//    public void testSelectExist() throws Exception {
//        System.out.println("testSelectExist");
//        StudentDaoTest d = new StudentDaoTest();
//        StudentDto expected = bob;
//        StudentDao dao = new StudentDao(url);
//        StudentDto result = dao.get(KEY);
//        assertEquals(expected, result);
//    }
//    
//    @Test
//    public void testSelectNotExist() throws Exception {
//        System.out.println("testSelectNotExist");
//        StudentDao dao = new StudentDao(url);
//        StudentDto result = dao.get(patrick.getKey());
//        assertNull(result);
//    }
//    
//    @Test
//    public void testSelectIncorrectParameter() throws Exception {
//        System.out.println("testSelectIncorrectParameter");
//        StudentDao dao = new StudentDao(url);
//        Integer incorrect = null;
//        assertThrows(RepositoryException.class, () -> {
//            dao.get(incorrect);
//        });
//    }
//    
//    @Test
//    public void testSelectWhenFileNotFound() throws Exception {
//        System.out.println("testSelectWhenFileNotFound");
//        String url = "test/NoFile.txt";
//        assertThrows(RepositoryException.class, () -> {
//            StudentDao dao = new StudentDao(url);
//            dao.get(KEY);
//        });
//    }
    
//    @Test
//    public void testInsertExist() throws Exception {
//        System.out.println("testInsertExist");
//        StudentDaoTest d = new StudentDaoTest();
//        StudentDao dao = new StudentDao(url);
//        assertThrows(RepositoryException.class, () -> {
//            dao.insert(bob);
//        });
//    }
//    
//    @Test
//    public void testInsertNotExist() throws Exception {
//        System.out.println("testInsertNotExist");
//        StudentDto expected = patrick;
//        StudentDao dao = new StudentDao(url);
//        dao.insert(patrick);
//        StudentDto result = dao.get(99999);
//        assertEquals(expected, result);
//    }
//    
//    @Test
//    public void testInsertIncorrectParameter() throws Exception {
//        System.out.println("testGetIncorrectParameter");
//        StudentDao dao = new StudentDao(url);
//        StudentDto incorrect = null;
//        assertThrows(RepositoryException.class, () -> {
//            dao.insert(incorrect);
//        });
//    }
//    
//    @Test
//    public void testInsertWhenFileNotFound() throws Exception {
//        System.out.println("testInsertWhenFileNotFound");
//        String url = "test/NoFile.txt";
//        assertThrows(RepositoryException.class, () -> {
//            StudentDao dao = new StudentDao(url);
//            dao.get(KEY);
//        });
//    }
    
//    @Test
//    public void testUpdateExist() throws Exception {
//        System.out.println("testUpdateExist");
//        StudentDaoTest d = new StudentDaoTest();
//        StudentDao dao = new StudentDao(url);
//        StudentDto expected = new StudentDto(94853, "Etoile", "Patrick");
//        dao.update(expected);
//        StudentDto result = dao.get(94853);
//        assertEquals(expected, result);
//    }
//    
//    @Test
//    public void testUpdateNotExist() throws Exception {
//        System.out.println("testUpdateNotExist");
//        StudentDao dao = new StudentDao(url);
//        StudentDto notExist = new StudentDto(19653, "wow", "absent");
//        assertThrows(RepositoryException.class, () -> {
//            dao.update(notExist);
//        });
//    }
//    
//    @Test
//    public void testUpdateIncorrectParameter() throws Exception {
//        System.out.println("testUpdateIncorrectParameter");
//        StudentDao dao = new StudentDao(url);
//        StudentDto incorrect = null;
//        assertThrows(RepositoryException.class, () -> {
//            dao.update(incorrect);
//        });
//    }
//    
//    @Test
//    public void testUpdateWhenFileNotFound() throws Exception {
//        System.out.println("testUpdateWhenFileNotFound");
//        String url = "test/NoFile.txt";
//        assertThrows(RepositoryException.class, () -> {
//            StudentDao dao = new StudentDao(url);
//            dao.update(patrick);
//        });
//    }
    
//    @Test
//    public void testDeleteExist() throws Exception {
//        System.out.println("testDeleteExist");
//        StudentDaoTest d = new StudentDaoTest();
//        StudentDao dao = new StudentDao(url);
//        dao.delete(64931);
//        StudentDto result = dao.get(64931);
//        assertNull(result);
//    }
//    
//    @Test
//    public void testDeleteNotExist() throws Exception {
//        System.out.println("testDeleteNotExist");
//        StudentDao dao = new StudentDao(url);
//        assertThrows(RepositoryException.class, () -> {
//            dao.delete(patrick.getKey());
//        });
//    }
//    
//    @Test
//    public void testDeleteIncorrectParameter() throws Exception {
//        System.out.println("testDeleteIncorrectParameter");
//        StudentDao dao = new StudentDao(url);
//        Integer incorrect = null;
//        assertThrows(RepositoryException.class, () -> {
//            dao.delete(incorrect);
//        });
//    }
//    
//    @Test
//    public void testDeleteWhenFileNotFound() throws Exception {
//        System.out.println("testUpdateWhenFileNotFound");
//        String url = "test/NoFile.txt";
//        assertThrows(RepositoryException.class, () -> {
//            StudentDao dao = new StudentDao(url);
//            dao.delete(KEY);
//        });
//    }
    
    @Test
    public void testGetAllExist() throws Exception {
        System.out.println("testGetAllExist");
        StudentDaoTest d = new StudentDaoTest();
        StudentDao dao = new StudentDao(url);
        List<StudentDto> l = dao.getAll();
        assertEquals(all, l);
    }
    
    @Test
    public void testDeleteWhenFileNotFound() throws Exception {
        System.out.println("testUpdateWhenFileNotFound");
        String url = "test/NoFile.txt";
        assertThrows(RepositoryException.class, () -> {
            StudentDao dao = new StudentDao(url);
            List<StudentDto> l = dao.getAll();
        });
    }
}
