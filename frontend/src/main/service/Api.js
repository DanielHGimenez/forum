import axios from 'axios';
import ApiConfig from '../config/ApiConfig.json'


export default class Api {

    static criarConta(nome, senha) {
        return axios.post(montarUrl() + '/autenticacao/cadastro', {
            nome : nome,
            senha : senha
        });
    }

    static logarConta(nome, senha) {
        return axios.get(montarUrl() + '/autenticacao/login', {
            params : {
                usuario : nome,
                senha : senha
            }
        }).then((response) => {
            return response.headers['authorization'];
        });
    }

    static buscarPerguntas(pagina) {
        return axios.get(montarUrl() + '/perguntas', {
            params : {
                pagina : pagina
            }
        });
    }

    static buscarPergunta(idPergunta) {
        return axios.get(montarUrl() + '/perguntas/' + idPergunta);
    }

    static publicarPergunta(token, pergunta) {
        return axios.post(montarUrl() + '/perguntas', {
            pergunta: pergunta
        }, {
            headers : {
                'Authorization' : token
            }
        });
    }

    static buscarRespostas(idPergunta, pagina) {
        return axios.get(montarUrl() + '/perguntas/' + idPergunta + '/respostas', {
            params : {
                pagina : pagina
            }
        });
    }

    static publicarResposta(token, idPergunta, resposta) {
        return axios.post(montarUrl() + '/perguntas/' + idPergunta + '/respostas', {
            resposta: resposta
        }, {
            headers : {
                'Authorization' : token
            }
        });
    }

}

const montarUrl = () => {
    if (ApiConfig.httpsEnabled) {
        return 'https://' + ApiConfig.endereco + ':' + ApiConfig.porta;
    }
    return 'http://' + ApiConfig.endereco + ':' + ApiConfig.porta;
};