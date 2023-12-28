package lk.ijse.teaFactory.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

    private String cusid;
    private String empid;
    private String cusname;
    private String cusAddress;
    private String cusCantac;
    //   private String isCompleted;
}
