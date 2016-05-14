package javacommon.base;
import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
  
/**  
 * ClassName:DateJsonSerializer <br/>  
 * Function: 日期类型格式化，格式化为：yyyy-MM-dd HH:mm:ss 格式. 用法如下：<br/>  
 * Reason:   @JsonSerialize(using=DateJsonSerializer.class) 
 *           @Column(name="BIRTHDAY") 
 *           public Date getBirthday() { 
 *              return birthday; 
 *           } 
 *          . <br/>  
 * Date:     2014年7月10日 下午1:26:08 <br/>  
 * @author   zhangzhaoyu  
 * @version   1.0 
 * @since    JDK 1.7  
 * @see        
 */  
public class DateJsonSerializer extends JsonSerializer<Date> {  
  
    @Override  
    public void serialize(Date value, JsonGenerator jgen,  
            SerializerProvider provider) throws IOException,  
            JsonProcessingException {  
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
         String formattedDate = formatter.format(value);  
         jgen.writeString(formattedDate);  
    }  
  
}  