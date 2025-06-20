package com.ictkerala.studentscore.StudentScore.repo;


import com.ictkerala.studentscore.StudentScore.model.StudentScoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StudentScorerepo extends JpaRepository<StudentScoreModel,Integer> {

    @Query(value = "SELECT `stud_id`, `name`, `score`, `subject` FROM `student_score_model` WHERE `stud_id=?1`",nativeQuery = true)
    StudentScoreModel findStudent(String id);

}
