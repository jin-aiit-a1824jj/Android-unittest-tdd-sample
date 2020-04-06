package a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.testdata;

import java.util.LinkedList;
import java.util.List;

import a1824jj.jp.ac.aiit.androidunittesttdd_sample.unit_test_android3.questions.Question;

public class QuestionsTestData {

    public static List<Question> getQuestions() {
        List<Question> questionSchemas = new LinkedList<>();
        questionSchemas.add(new Question("id1","title1"));
        questionSchemas.add(new Question("id2","title2"));
        return questionSchemas;
    }

    public static Question getQuestion() {
        return new Question("id","title");
    }
}
