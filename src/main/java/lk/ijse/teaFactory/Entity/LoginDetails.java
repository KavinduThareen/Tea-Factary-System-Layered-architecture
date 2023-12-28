package lk.ijse.teaFactory.Entity;

import lombok.*;

import java.sql.Date;

@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Data
public class LoginDetails {
    private String id;
    private String inTime;
    private Date date;
}
