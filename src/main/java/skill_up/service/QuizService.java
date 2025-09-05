package skill_up.service;


import skill_up.entity.QuizQuestion;
import skill_up.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepo questionRepo;


    public List<QuizQuestion> getQuestionsBySubject(String subjectName)
    {
        List<QuizQuestion> questionRepoAll = questionRepo.findBySubjectName(subjectName);
        return questionRepoAll;
    }

    public QuizQuestion saveQuestion(QuizQuestion question){
        QuizQuestion save = questionRepo.save(question);
        return save;
    }
}
