import com.tg.manager.controller.SubmitController;
import com.tg.manager.model.DisplayTableModel;
import com.tg.manager.model.SubmitModel;
import com.tg.manager.model.TGModel;
import org.junit.Test;

import java.util.Date;

public class SubmitTest {

    @Test
    public void createSubmit(){
        Date now = new Date();
        SubmitModel.submitValidator("Teste 03", "11-10-2020", "12-10-2020", "TG2", "Técnico - Disciplina");
    }

    @Test
    public void validateMethodGetDescriptionTGModel(){
        TGModel.getModelTg(1);
    }

    @Test
    public void validateMethodTypeTGAndModelTg(){
        System.out.println(DisplayTableModel.getNoteAndFeedback(2, "Relatório Técnico - Estágio (Somente para quem não pode participar de 6 APIs. Autorizado pela empresa)"));
    }

    @Test
    public void deleteSubmit(){
        // Test for delete of submit
        SubmitController.deleteInDb(10);
        SubmitController.deleteInDb(9);
        SubmitController.deleteInDb(8);
    }
}
