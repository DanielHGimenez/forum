package br.com.dhg.testebexs.dto;

import java.util.HashMap;

/**
 * Essa classe deve ser usada como embrulho para retornar valores unicos nas controllers
 * @param <T>
 */
public class Wrapper<T> extends HashMap<String, T> {

    public Wrapper(String nomeAtributo, T valorAtributo) {
        this.put(nomeAtributo, valorAtributo);
    }

}
