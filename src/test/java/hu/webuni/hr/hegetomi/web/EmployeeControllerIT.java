package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

    private static final String BASE_URI = "/api/employees";

    @Autowired
    WebTestClient webTestClient;

    /*@Test
    void postFutureEmployee() {
        EmployeeDto employeeDto = new EmployeeDto(5, "Name", "Title", 500,
                LocalDateTime.of(3000, 12, 10, 10, 10));
        webTestClient.post().uri(BASE_URI).bodyValue(employeeDto).exchange().expectStatus().isEqualTo(400);
    }

    @Test
    void postNullEmployee() {
        EmployeeDto employeeDto = new EmployeeDto(5, null, "Title", 500,
                LocalDateTime.of(2000, 12, 10, 10, 10));
        webTestClient.post().uri(BASE_URI).bodyValue(employeeDto).exchange().expectStatus().isEqualTo(400);
    }

    @Test
    void postZeroPayEmployee() {
        EmployeeDto employeeDto = new EmployeeDto(5, "Name", "Title", 0,
                LocalDateTime.of(2000, 12, 10, 10, 10));
        webTestClient.post().uri(BASE_URI).bodyValue(employeeDto).exchange().expectStatus().isEqualTo(400);
    }

    @Test
    void putZeroPayEmployee() {
        EmployeeDto employeeDto = new EmployeeDto(0, "Name", "Title", 0,
                LocalDateTime.of(2000, 12, 10, 10, 10));
        webTestClient.put().uri(BASE_URI + "/" + employeeDto.getId())
                .bodyValue(employeeDto).exchange().expectStatus().isEqualTo(400);
    }

    @Test
    void putValidEmployee() {
        EmployeeDto employeeDto = new EmployeeDto(0, "Name", "Title", 100,
                LocalDateTime.of(2000, 12, 10, 10, 10));
        webTestClient.put().uri(BASE_URI + "/" + employeeDto.getId())
                .bodyValue(employeeDto).exchange().expectStatus().isEqualTo(200);
    }
    @Test
    void postValidEmployee() {
        EmployeeDto employeeDto = new EmployeeDto(8, "Name", "Title", 100,
                LocalDateTime.of(2000, 12, 10, 10, 10));
        webTestClient.post().uri(BASE_URI)
                .bodyValue(employeeDto).exchange().expectStatus().isEqualTo(200);
    }

    @Test
    void postValidExistingEmployee() {
        EmployeeDto employeeDto = new EmployeeDto(0, "Name", "Title", 100,
                LocalDateTime.of(2000, 12, 10, 10, 10));
        webTestClient.post().uri(BASE_URI)
                .bodyValue(employeeDto).exchange().expectStatus().isEqualTo(403);
    }


     */

}
