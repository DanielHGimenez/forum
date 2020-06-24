import React, { useState, useEffect } from 'react'
import {
    Link,
    useParams
} from "react-router-dom";
import { Container, Row, Col } from 'react-bootstrap';
import Pergunta from '../component/Pergunta';
import Resposta from '../component/Resposta';
import Api from '../service/Api';


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

    useEffect(() => {
        irParaPagina(paginaAtual);
    }, []);

    return (
        <Container>
            <Row className="my-4">
                <Col>
                    <Link to="/">Voltar</Link>
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
        </Container>
    )
}

