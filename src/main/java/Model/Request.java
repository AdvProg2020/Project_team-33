package Model;

import java.util.ArrayList;

public class Request {
    public static ArrayList<Request> allRequest = new ArrayList<Request>();

}
enum RequestState{
    REJECTED,
    PENDING,
    CONFIRMED,
}
