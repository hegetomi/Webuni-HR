package hu.webuni.hr.hegetomi.exception;

public class EmployeeIsEmployedException extends RuntimeException{
    public EmployeeIsEmployedException(Long id){
        super("Employee with id: " + id + " is already employed.");
    }
}
