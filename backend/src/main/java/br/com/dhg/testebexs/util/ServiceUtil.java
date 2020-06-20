package br.com.dhg.testebexs.util;

public class ServiceUtil {

    /**
     * No Spring Data JPA o numero da pagina começa em 0 enquanto na API começa em 1,
     * e por isso subtraimos 1 para igualar ao valor correto.
     */
    public static Integer corrigirNumeroPagina(Integer pagina) {

        return pagina - 1;

    }

}
