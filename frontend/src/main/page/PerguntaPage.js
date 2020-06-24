import React, { useState, useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import PublicarTexto from '../component/PublicarTexto';
import Paginacao from '../component/Paginacao';
import Pergunta from '../component/Pergunta';
import Api from '../service/Api';
import { CredenciaisActions as Actions } from '../reducer/CredenciaisActions.js';
import '../style/PerguntaPage.css';


export default function PerguntaPage({ store }) {

    const [ paginaAtual, setPaginaAtual ] = useState(1);
    const [ totalPaginas, setTotalPaginas ] = useState(1);
    const [ perguntas, setPerguntas ] = useState([]);

    const irParaPagina = (pagina) => {
        Api.buscarPerguntas(pagina)
        .then(response => {
            setPerguntas(response.data.perguntas);
            setPaginaAtual(response.data.paginaAtual);
            setTotalPaginas(response.data.totalPaginas);
        })
        .catch((erro => {
            console.log(erro);
        }));
    };

    const onSubmit = (pergunta) => {
        Api.publicarPergunta(store.getState().credenciais, pergunta)
        .then((response) => {
            irParaPagina(paginaAtual);
        })
        .catch((erro => {
            if (erro.response.status == 403) {
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
        <Container className="Principal">
            <Row className="my-3">
                <PublicarTexto store={ store } onSubmit={ onSubmit } labelModal="Digite sua pergunta a seguir:" />
            </Row>
            { 
                perguntas && perguntas.map((pergunta, index) => {
                    return (
                        <Row className="mb-2">
                            <Pergunta
                                key={ index }
                                link={ true }
                                limit={ true }
                                id={ pergunta.id }
                                texto={ pergunta.texto }
                                quantidadeRespostas={ pergunta.quantidadeRespostas }
                            />
                        </Row>
                    );
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
