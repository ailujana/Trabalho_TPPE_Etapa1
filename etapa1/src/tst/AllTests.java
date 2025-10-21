package tst;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory({Funcional.class, Excecao.class}) 
@SuiteClasses({ 
    tstAplicarPartidaNaTabela.class, 
    TstExceptionRodada.class,
    TstJogoRepetidoRodada.class,
    tstPartidaExcecao.class, 
    tstPartidaResultado.class,
    TstPontuacao.class,
    TstSorteioRodada.class,
    tstRanking.class
})
public class AllTests { 
    
}