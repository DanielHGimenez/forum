import React, { useState, useEffect } from 'react'
import {
    Link,
    useParams
} from "react-router-dom";
import { Container, Row, Col } from 'react-bootstrap';
import Pergunta from '../component/Pergunta';
import Resposta from '../component/Resposta';
import Paginacao from '../component/Paginacao';
import PublicarTexto from '../component/PublicarTexto';
import { CredenciaisActions as Actions } from '../reducer/CredenciaisActions.js';
import Api from '../service/Api';
import '../style/RespostaPage.css';


export default function RespostaPage({ store }) {

    let { id } = useParams();
    const [ pergunta, setPergunta ] = useState({});
    const [ respostas, setRespostas ] = useState([]);
    const [ paginaAtual, setPaginaAtual ] = useState(1);
    const [ totalPaginas, setTotalPaginas ] = useState(1);

    const irParaPagina = (pagina) => {
        Api.buscarPergunta(id)
        .then(response => {
            setPergunta(response.data);
        })
        .catch((erro => {
            console.log(erro);
        }));

        Api.buscarRespostas(id, pagina)
        .then((response) => {
            setRespostas(response.data.respostas);
            setPaginaAtual(response.data.paginaAtual);
            setTotalPaginas(response.data.totalPaginas);
        })
        .catch((erro => {
            console.log(erro);
        }));
    };

    const onSubmit = (resposta) => {
        Api.publicarResposta(store.getState().credenciais, id, resposta)
        .then((response) => {
            irParaPagina(paginaAtual);
        })
        .catch((erro => {
            if (erro.response.status === 403) {
                store.dispatch({
                    type: Actions.LIMPAR_CREDENCIAIS
                });
            }
            console.log(erro);
        }));
    };

    useEffect(() => {
        irParaPagina(paginaAtual);
    }, []);

    return (
        <Container className="RespostaPage">
            <Row className="my-4">
                <Col className="d-flex">
                    <Link className="voltar mr-2" to="/">
                        <span className="material-icons">
                            keyboard_arrow_left
                        </span>
                        <span className="texto">Voltar</span>
                    </Link>
                    <PublicarTexto store={ store } onSubmit={ onSubmit } labelModal="Digite sua resposta a seguir:" />
                </Col>
            </Row>
            <Row>
                <Col>
                    <h2>Pergunta</h2>
                </Col>
            </Row>
            <Row>
                <Col>
                    <Pergunta
                        link={ true }
                        limit={ true }
                        id={ pergunta.id }
                        texto={ pergunta.texto }
                        quantidadeRespostas={ pergunta.quantidadeRespostas }
                    />
                </Col>
            </Row>
            <Row className="mt-5">
                <Col>
                    <h2>Respostas</h2>
                </Col>
            </Row>
            {
                respostas.map((resposta, index) => {
                    return (
                        <Row className="mb-3" id={ index }>
                            <Col>
                                <Resposta texto={ resposta.texto } />
                            </Col>
                        </Row>
                    )
                })
            }
            <Row>
                <Col className="d-flex">
                    <Paginacao className="mx-auto" paginaAtual={ paginaAtual } totalPaginas={ totalPaginas } onClick={ irParaPagina } />
                </Col>
            </Row>    
        </Container>
    )
}

