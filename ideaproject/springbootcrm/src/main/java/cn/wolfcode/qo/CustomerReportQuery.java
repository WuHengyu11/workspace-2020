package cn.wolfcode.qo;

import cn.wolfcode.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class CustomerReportQuery extends QueryObject{
    private String groupType = "e.name";
    private String keyword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public Date getEndDate(){
        //
        return DateUtil.getEndDate(endDate);
    }
}
