package pl.tcs.po.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String nameOfObject, int id){
        super(nameOfObject + " with id = " + id + " doesn't exist");
    }

    public NotFoundException(String nameOfObject, String name){
        super(nameOfObject + " with name = " + name + " doesn't exist");
    }
}
