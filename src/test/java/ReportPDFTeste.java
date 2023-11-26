import com.tg.manager.model.AdvisorModel;
import com.tg.manager.model.DisplayTableModel;
import com.tg.manager.model.StudentModel;
import com.tg.manager.utils.ReportPdf;
import org.junit.Test;

public class ReportPDFTeste {

    @Test
    public void getReport(){
        StudentModel.getReport();
    }

    @Test
    public void getReportIsApt(){
        DisplayTableModel.reportIsApt();
    }

    @Test
    public void getReportCertified(){
        AdvisorModel.reportCertified();
    }
}
