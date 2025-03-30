package org.aryak.springdata.repository;

import org.aryak.springdata.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "from Student")
    List<Student> findAllStudents();

    @Query(value = "from Student where name like %:name%")
    List<Student> findStudentsWithNameLike(@Param(value = "name") String name);

    @Query(value = "from Student where id between :min and :max")
    List<Student> findStudentsBetweenRange(@Param(value = "min") int min, @Param(value = "max") int max);
}
