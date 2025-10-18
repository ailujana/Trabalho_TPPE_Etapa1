package tst;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.Time;
import exceptions.TipoInvalidoException;

@Category(Funcional.class)
@RunWith(Parameterized.class)
public class TstPontuacao extends TstBasePontuacao {

    char tipoResultado;
    int golsPro;
    int golsContraOuMesmo;
    int expPontos;
    int expVitorias;
    int expGolsPro;
    int expGolsContra;
    int expSaldo;

    public TstPontuacao(char tipoResultado, int golsPro, int golsContraOuMesmo,
                        int expPontos, int expVitorias, int expGolsPro, int expGolsContra, int expSaldo) {
        this.tipoResultado = tipoResultado;
        this.golsPro = golsPro;
        this.golsContraOuMesmo = golsContraOuMesmo;
        this.expPontos = expPontos;
        this.expVitorias = expVitorias;
        this.expGolsPro = expGolsPro;
        this.expGolsContra = expGolsContra;
        this.expSaldo = expSaldo;
    }

    @Parameters
    public static Iterable<Object[]> getParameters(){
        Object[][] parametros = new Object[][] {
            {'V', 2, 0, 3, 1, 2, 0, 2},
            {'E', 1, 1, 1, 0, 1, 1, 0},
            {'D', 1, 3, 0, 0, 1, 3, -2}
        };
        return Arrays.asList(parametros);
    }

    @Test
    @Category(Funcional.class)
    public void testaPontuacaoUnitario() throws TipoInvalidoException {
        switch (tipoResultado) {
            case 'V':
                t.registrarVitoria(golsPro, golsContraOuMesmo);
                break;
            case 'E':
                t.registrarEmpate(golsPro);
                break;
            case 'D':
                t.registrarDerrota(golsPro, golsContraOuMesmo);
                break;
            default:
                throw new AssertionError("Tipo de resultado inválido: " + tipoResultado);
        }

        assertEquals(expPontos, t.getPontos());
        assertEquals(expVitorias, t.getnVitorias());
        assertEquals(expGolsPro, t.getGolsMarcados());
        assertEquals(expGolsContra, t.getGolsSofridos());
        assertEquals(expSaldo, t.getSaldoGols());
    }


    @Test
    public void setNomeValido() throws Exception {
        Time t = new Time(" Botafogo ");
        t.setNome("   Botafogo FR   ");
        assertEquals("Botafogo FR", t.getNome());
    }

    @Test(expected = TipoInvalidoException.class)
    public void setNomeVazioFalha() throws Exception {
        Time t = new Time("Criciuma");
        t.setNome("   ");
    }

    @Test(expected = TipoInvalidoException.class)
    public void setPontosNegativosFalha() throws Exception {
        Time t = new Time("Bahia");
        t.setPontos(-1);
    }

    @Test(expected = TipoInvalidoException.class)
    public void setVitoriasNegativasFalha() throws Exception {
        Time t = new Time("Athletico");
        t.setnVitorias(-1);
    }

    @Test(expected = TipoInvalidoException.class)
    public void setGolsMarcadosNegativoFalha() throws Exception {
        Time t = new Time("Goias");
        t.setGolsMarcados(-5);
    }

    @Test(expected = TipoInvalidoException.class)
    public void setGolsSofridosNegativoFalha() throws Exception {
        Time t = new Time("Fortaleza");
        t.setGolsSofridos(-3);
    }
}
