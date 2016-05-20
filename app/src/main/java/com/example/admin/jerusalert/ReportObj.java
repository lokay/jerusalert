import java.time.LocalDateTime;
import java.util.Date;


public class ReportObj {
	public int Id;
	public String Category;
	public String Subcategory;
	public int Location_x;
	public int Location_y;
	public LocalDateTime Report_time;
	public String Report_text;
	public String Phone;
	public int Counter;

	public ReportObj() {
		Id = 0;
		Category = "";
		Subcategory = "";
		Location_x = 0;
		Location_y = 0;
		Report_time = LocalDateTime.now();
		Report_text = "";
		Phone = "";
		Counter = 0;
	}

}
