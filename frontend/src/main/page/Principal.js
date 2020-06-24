import React, { useState, useEffect } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import { Link } from "react-router-dom";
import PublicarPergunta from '../component/PublicarPergunta';
import Paginacao from '../component/Paginacao'
import Api from '../service/Api';
import '../style/Principal.css';


export default function Principal({ store }) {

    const [ paginaAtual, setPaginaAtual ] = useState(1);
    const [ totalPaginas, setTotalPaginas ] = useState(1);
    const [ perguntas, setPerguntas ] = useState([]);

    const irParaPagina = (pagina) => {
        Api.buscarPerguntas(store.getState().credenciais, pagina)
        .then(response => {
            setPerguntas(response.data.perguntas);
            setPaginaAtual(response.data.paginaAtual);
            setTotalPaginas(response.data.totalPaginas);
        })
        .catch((erro => {
            console.log(erro);
        }));
    };

    const onPublicarPergunta = () => {
        irParaPagina(paginaAtual);
    };

    useEffect(() => {
        irParaPagina(paginaAtual);
    }, []);

    return (
        <Container className="Principal">
            <Row className="my-3">
                <PublicarPergunta store={ store } onPublicar={ onPublicarPergunta } />
            </Row>
            { 
                perguntas && perguntas.map((pergunta, index) => {
                    return (
                        <Row key={index} className="mb-2 px-2 py-3 pergunta">
                            <Container>
                                <Row className="mb-2">
                                    <Col className="d-flex">
                                        <Link className="texto" to={ "/perguntas/" + pergunta.id }>
                                            { pergunta.texto }
                                        </Link>
                                    </Col>
                                </Row>
                                <Row className="about">
                                    <Col className="ml-2">
                                        Quantidade de respostas: { pergunta.quantidadeRespostas }
                                    </Col>
                                </Row>
                            </Container>
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
