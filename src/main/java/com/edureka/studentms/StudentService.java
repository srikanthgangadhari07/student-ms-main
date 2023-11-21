package com.edureka.studentms;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository repository;
    
    WebClient webClient= WebClient.create("http://localhost:8055/payment-service/api/v1");
    
    public void saveStudent(Student student) {
        repository.save(student);
    }

    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    public List<Student> findAllStudentsBySchool(Integer schoolId) {
        return repository.findAllBySchoolId(schoolId);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @SuppressWarnings("unchecked")
    public ResponseEntity<Student> updateStudent(Student student){
        Optional<Student> studentDb = this.repository.findById(student.getId());
        if(studentDb.isPresent()) {
            Student studentUpdate = studentDb.get();
            studentUpdate.setFirstname(student.getFirstname());
            studentUpdate.setLastname(student.getLastname());
            studentUpdate.setEmail(student.getEmail());
            studentUpdate.setSchoolId(student.getSchoolId());
            repository.save(studentUpdate);

            return ResponseEntity.ok().body(studentUpdate);
        }
        else {
            return (ResponseEntity<Student>) ResponseEntity.badRequest();
        }

    }
    
    //Service for Student Fee payment
    
    public Mono<ResponseEntity<String>> payFeeService(Payment payment){
    	
    	return webClient.post().uri("/feePayment")
    			.contentType(MediaType.APPLICATION_JSON)
    			.bodyValue(payment)
    			.retrieve()
    			.toEntity(String.class);
    }
    
    //Fetching the Total Fee Paid by Student
    
    public ResponseEntity<Mono<String>> feeDetailsService(Integer studentId){
    	return ResponseEntity.ok(webClient.get()
    			.uri("/paymentDetails/{studentId}",studentId)
    			.retrieve()
    			.bodyToMono(String.class));
    }
}
