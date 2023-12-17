package com.example.studentapi.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class StudentRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        rest("/students")
                .get().to("direct:getStudents")
                .post().to("direct:addStudent");

        rest("/students/{id}")
                .get().to("direct:getStudent")
                .put().to("direct:updateStudent")
                .delete().to("direct:deleteStudent");

        from("direct:getStudents")
                .to("jpa:com.example.studentapi.model.Student")
                .log("Retrieved all students: ${body}");

        from("direct:getStudent")
                .setHeader("CamelJpaQuery", simple("select s from Student s where s.id = ${header.id}"))
                .to("jpa:com.example.studentapi.model.Student")
                .log("Retrieved student with id ${header.id}: ${body}");

        from("direct:addStudent")
                .to("jpa:com.example.studentapi.model.Student")
                .log("Added new student: ${body}");

        from("direct:updateStudent")
                .to("jpa:com.example.studentapi.model.Student")
                .log("Updated student with id ${header.id}: ${body}");

        from("direct:deleteStudent")
                .setHeader("CamelJpaQuery", simple("delete from Student s where s.id = ${header.id}"))
                .to("jpa:com.example.studentapi.model.Student")
                .log("Deleted student with id ${header.id}");
    }
}