package tst;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory({Funcional.class, Excecao.class}) 
@SuiteClasses({ 
    TstAplicarPartidaNaTabela.class, 
    TstExceptionRodada.class,
    TstJogoRepetidoRodada.class,
    TstPartidaExcecao.class, 
    TstPartidaResultado.class,
    TstPontuacao.class,
    TstSorteioRodada.class,
    TstRankingPorRodada.class
})
public class AllTests { 
    
}