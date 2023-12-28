package lk.ijse.teaFactory.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Suppling{

    private String id;
    private String sId;
    private Date date;
    private String weigth;
    private double  payment;

}
