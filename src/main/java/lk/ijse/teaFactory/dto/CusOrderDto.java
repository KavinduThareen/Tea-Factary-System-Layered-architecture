package lk.ijse.teaFactory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CusOrderDto {
    private String id;
    private String cId;
    private String catagary;
    private double weigth;
    private String date;
    private String descreption;
    private double payment;
 //   private String isCompleted;
}
