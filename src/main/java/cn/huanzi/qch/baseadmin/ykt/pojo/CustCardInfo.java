package cn.huanzi.qch.baseadmin.ykt.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "V_CUSTCARDINFO")
@Data
public class CustCardInfo implements Serializable {
    @Id
    private String stuempno;

    private String custname;

    private String cardno;

    private String cardstatus;

    private Date statusname;

    private Date showcardno;

}

