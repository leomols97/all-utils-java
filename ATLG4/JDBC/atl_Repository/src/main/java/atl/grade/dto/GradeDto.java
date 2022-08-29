package atl.grade.dto;

/**
 *
 * @author jlc
 */
public class GradeDto extends Dto<Integer> {

    private int value;
    private String lesson;

    public GradeDto(Integer key, int score, String lesson) {
        super(key);
        value = score;
        this.lesson = lesson;
    }

    public int getValue() {
        return value;
    }

    public String getLesson() {
        return lesson;
    }

}
