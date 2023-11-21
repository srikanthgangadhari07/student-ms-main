package com.edureka.studentms;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StudentMsApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder().profiles("dev").sources(StudentMsApplication.class).run(args);
	}

}

