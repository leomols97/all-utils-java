package g56133.mentoring.repository;

import g56133.atl.Mentoring.config.ConfigManager;
import g56133.atl.Mentoring.dto.StudentDto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leong Paeg-Hing
 */
public class StudentDao implements Dao<StudentDto, Integer> {

    private String url;
    
    private StudentDao() {
        try {
            ConfigManager.getInstance().load();
        } catch (RepositoryException e) {
            e.getMessage();
        }
        this.url = ConfigManager.getInstance().getProperties("file.url");
    }
    
    public static StudentDao getInstance() {
        return StudentDaoHolder.INSTANCE;
    }
    
    StudentDao(String url) {
        System.out.println(url);
        this.url = url;
    }
    
    @Override
    public void insert(StudentDto item) throws RepositoryException{
        if(item == null) {
            throw new RepositoryException("There is no parameter " + item);
        }else if(get(item.getKey()) != null) {
            throw new RepositoryException
        ("The student " + item.getKey() + " is already inserted.");
        }

        String data = "";
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this.url, true));
            data = data + item.getKey() + "," + item.getLastName()
                    + "," + item.getFirstName();
            writer.append("\n");
            writer.append(data);
            writer.close();
        } catch (IOException ex) {
            throw new RepositoryException(ex);
        }
    }

    @Override
    public void delete(Integer key) throws RepositoryException{  
        if (key == null) {
            throw new RepositoryException("There is no key");
        }
        StudentDto s = get(key);
        if(s == null) {
            throw new RepositoryException("You can't delete a student that doesn't exist.");
        }
        
        boolean test = get(key) == null;
        System.out.println(test);
        
        System.out.println("Ca passe");

        StringBuffer inputBuffer;
        try ( BufferedReader file = new BufferedReader(new FileReader(url))) {

            inputBuffer = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                if (!line.substring(0, 5).equalsIgnoreCase("" + key)) {
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
            }

            file.close();
            String inputStr = inputBuffer.toString();

            try ( FileOutputStream fileOut = new FileOutputStream(url)) {
                System.out.println("ok");
                fileOut.write(inputStr.getBytes());
                fileOut.close();
            }
        } catch (IOException ex) {
            throw new RepositoryException(ex);
        }
    }

    @Override
    public StudentDto get(Integer key) throws RepositoryException{
        if (key == null) {
            throw new RepositoryException("There is no key.");
        }

        try ( BufferedReader file = new BufferedReader(new FileReader(url))) {
            String line;

            while ((line = file.readLine()) != null) {
                if (line.substring(0, 5).equalsIgnoreCase("" + key)) {
                    String[] data = line.split(",", 0);
                    file.close();
                    return new StudentDto(Integer.parseInt(data[0]),
                            data[1], data[2]);
                }
            }

        } catch (IOException ex) {
            throw new RepositoryException(ex);
        }

        return null;
    }

    @Override
    public List<StudentDto> getAll() throws RepositoryException{
        List<StudentDto> l = new ArrayList<>();

        try ( BufferedReader file = new BufferedReader(new FileReader(this.url))) {
            String line;

            while ((line = file.readLine()) != null) {
                String[] data = line.split(",", 0);
                l.add(new StudentDto(Integer.parseInt(data[0]),
                        data[1], data[2]));
            }

        } catch (FileNotFoundException ex) {
            throw new RepositoryException(ex);
        } catch (IOException ex) {
            throw new RepositoryException(ex);
        }
        return l;
    }

    @Override
    public void update(StudentDto item) throws RepositoryException{
        if (item == null) {
            throw new RepositoryException("There is no parameter " + item);
        }else if(get(item.getKey()) == null) {
            throw new RepositoryException("The student " 
                    + item.getKey() + " doesn't exist.");
        }
        
        StringBuffer inputBuffer;
        try ( BufferedReader file = new BufferedReader(new FileReader(url))) {

            inputBuffer = new StringBuffer();
            String replaceLine = null;
            String line;

            while ((line = file.readLine()) != null) {
                if (line.substring(0, 5).equalsIgnoreCase("" + item.getKey())) {
                    replaceLine = line;
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }

            file.close();
            String inputStr = inputBuffer.toString();
            inputStr = inputStr.replaceAll(replaceLine, item.toString());

            try ( FileOutputStream fileOut = new FileOutputStream(url)) {
                fileOut.write(inputStr.getBytes());
                fileOut.close();
            }
        } catch (FileNotFoundException ex) {
            throw new RepositoryException(ex);
        } catch (IOException ex) {
            throw new RepositoryException(ex);
        }
    }
    
    private static class StudentDaoHolder {
        private static final StudentDao INSTANCE = new StudentDao();
    }

}
