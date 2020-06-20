package br.com.dhg.testebexs.util;

public class ControllerUtil {

    public static Integer normalizarNumeroPagina(Integer numeroPagina) {

        if (numeroPagina == null || numeroPagina < 1)
            numeroPagina = 1;

        return numeroPagina;

    }

}
