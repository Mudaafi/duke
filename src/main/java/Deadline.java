import java.util.Date;
import java.text.SimpleDateFormat;

class Deadline extends Task {
    private Date datetime;

    // Initialization
    Deadline(String name) {
        super(name);
        this.type = "D";
        this.recordTaskDetails(name);
        this.parseDateTime();
    }

    private void parseDateTime() {
        SimpleDateFormat formatx = new SimpleDateFormat("dd/mm/yyyy HHmm");
	if (this.detailDesc == null) {
		return;
	}
        if (this.detailDesc.equals("by")) {
            try {
                this.datetime = formatx.parse(this.taskDetails);
                System.out.println("Date Interpreted: " + formatx.format(this.datetime));
            } catch (Exception e) {
                System.out.println("Invalid Input. Unable to interpret Datetime");
                this.datetime = new Date();
            }
        }
    }
}
