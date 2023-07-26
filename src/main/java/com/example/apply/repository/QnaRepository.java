package com.example.apply.repository;

<<<<<<< HEAD
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> c6e926bc8e25f2431dc63482fe5f778d5d961c7c
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apply.entity.Qna;

public interface QnaRepository extends JpaRepository<Qna, Long>{

<<<<<<< HEAD
=======
	Page<Qna> findAll(Pageable pageable);
	
//	Qna findById(Long id);
	

	
	
>>>>>>> c6e926bc8e25f2431dc63482fe5f778d5d961c7c
}
