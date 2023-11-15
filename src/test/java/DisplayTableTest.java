import com.tg.manager.model.DisplayTableModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class DisplayTableTest {

    @Test
    public void getDataTable(){
       for(DisplayTableModel info : DisplayTableModel.getDataTable()){
           System.out.println(info);
           break;
       }
    }

    @Test
    public void columnTypeTg(){
        Assert.assertEquals(null, DisplayTableModel.descriptionTg("Olá"));
        Assert.assertEquals(null, DisplayTableModel.descriptionTg(""));
        Assert.assertEquals(null, DisplayTableModel.descriptionTg("Portfóli (Exige participação em todos os 6 APIs)"));
        Assert.assertEquals("Portfólio", DisplayTableModel.descriptionTg("Portfólio (Exige participação em todos os 6 APIs)"));
        Assert.assertEquals("Científico", DisplayTableModel.descriptionTg("Artigo Tecnológico ou Científico"));
        Assert.assertEquals("Técnico - Disciplina", DisplayTableModel.descriptionTg("Relatório Técnico - Disciplina (Somente para quem não pode participar de 6 APIs)"));
        Assert.assertEquals("Estágio - Técnico", DisplayTableModel.descriptionTg("Relatório Técnico - Estágio (Somente para quem não pode participar de 6 APIs. Autorizado pela empresa)"));

    }

    @Test
    public void filterDisplayTable(){
        System.out.println(DisplayTableModel.filterTable(null).size());
        System.out.println(DisplayTableModel.filterTable("Portfólio").size());
        System.out.println(DisplayTableModel.filterTable("Estágio - Técnico").size());
        System.out.println(DisplayTableModel.filterTable("Técnico - Disciplina").size());
        System.out.println(DisplayTableModel.filterTable("Científico").size());
    }


}
