package skill_up.repo;


import skill_up.entity.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends JpaRepository<QuizQuestion,Long> {
    List<QuizQuestion> findBySubjectName(String subject_name);
}
