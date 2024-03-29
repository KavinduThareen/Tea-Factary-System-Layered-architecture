package lk.ijse.teaFactory.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Salary {
    private String id;
    private String empId;
    private Date date;
    private String count;
}
