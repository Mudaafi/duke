import java.util.Date;
import java.text.SimpleDateFormat;

class Deadline extends Task {
    private Date datetime;

    // Initialization
    Deadline(String name) {
        super(name);
        this.taskType = TaskType.DEADLINE;
        this.recordTaskDetails(name);
        try {
            this.parseDateTime();
        } catch  (DukeException invalidInput) {
            invalidInput.printStackTrace();
        }

    }

    private void parseDateTime() throws DukeException {
        SimpleDateFormat formatx = new SimpleDateFormat("dd/mm/yyyy HHmm");
        if (this.detailDesc == null) {
            return;
        }
        if (this.detailDesc.equals("by")) {
            try {
                this.datetime = formatx.parse(this.taskDetails);
                System.out.println("Date Interpreted: " + formatx.format(this.datetime));
            } catch (Exception e) {
                //System.out.println("Invalid Input. Unable to interpret Datetime (use: dd/mm/yyyy HHmm)");
                this.datetime = new Date();
                throw new DukeException("Invalid Input. Unable to interpret Datetime (use: dd/mm/yyyy HHmm)");
            }
        }
    }
}
